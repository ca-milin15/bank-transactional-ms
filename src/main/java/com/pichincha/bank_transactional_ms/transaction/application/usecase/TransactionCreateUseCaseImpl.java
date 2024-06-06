package com.pichincha.bank_transactional_ms.transaction.application.usecase;

import com.pichincha.bank_transactional_ms.account.domain.Account;
import com.pichincha.bank_transactional_ms.shared.application.usecase.CommandUseCase;
import com.pichincha.bank_transactional_ms.shared.application.usecase.QueryUseCase;
import com.pichincha.bank_transactional_ms.shared.infrastructure.config.SystemPropertiesConfig;
import com.pichincha.bank_transactional_ms.transaction.application.dto.TransactionDTO;
import com.pichincha.bank_transactional_ms.transaction.application.exceptions.BusinessRulesRuntimeException;
import com.pichincha.bank_transactional_ms.transaction.application.transactional.TransactionService;
import com.pichincha.bank_transactional_ms.transaction.domain.enums.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("TransactionCreateUseCase")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class TransactionCreateUseCaseImpl implements CommandUseCase <TransactionDTO> {

    @Qualifier("AccountGetByIdentificationUseCase")
    QueryUseCase<Account> accountGetByIdentificationUseCase;
    TransactionService transactionService;
    SystemPropertiesConfig systemPropertiesConfig;

    @Override
    public TransactionDTO executeCommand(TransactionDTO transactionDTO) {
        var errors = systemPropertiesConfig.getMessages().getError();
        var accountToFind = Account.builder().customerIdentification(transactionDTO.getCustomerIdentification()).build();
        var accountFound = accountGetByIdentificationUseCase.executeQuery(accountToFind);
        var txType = TransactionTypeEnum.getEnumByCode(transactionDTO.getType(), errors.getTxTypeCode(), errors.getTxTypeMsj());
        businessRules(accountFound, transactionDTO, txType);
        var created_tx = transactionService.createTransaction(transactionDTO.toTransaction(accountFound, txType));
        return created_tx.toDTO();
    }

    private void businessRules(Account accountFound, TransactionDTO transactionDTO, TransactionTypeEnum txType) {
        balanceValidation(accountFound, transactionDTO, txType);
    }

    private void balanceValidation(Account accountFound, TransactionDTO transactionDTO, TransactionTypeEnum txType) {
        if(txType == TransactionTypeEnum.DEBITO) {
            var balanceComparison = accountFound.getBalance().compareTo(transactionDTO.getAmount());
            if(balanceComparison < 0) {
                var messages = systemPropertiesConfig.getMessages().getError();
                throw new BusinessRulesRuntimeException(messages.getBalanceInsufficientCode(),
                        messages.getBalanceInsufficientMsj());
            }
        }
    }
}
