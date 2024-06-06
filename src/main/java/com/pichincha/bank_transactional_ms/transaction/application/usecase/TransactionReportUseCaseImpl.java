package com.pichincha.bank_transactional_ms.transaction.application.usecase;

import com.pichincha.bank_transactional_ms.shared.application.exceptions.EntityNotFoundRuntimeException;
import com.pichincha.bank_transactional_ms.shared.application.usecase.QueryUseCase;
import com.pichincha.bank_transactional_ms.shared.infrastructure.config.SystemPropertiesConfig;
import com.pichincha.bank_transactional_ms.transaction.application.transactional.TransactionService;
import com.pichincha.bank_transactional_ms.transaction.domain.Transaction;
import com.pichincha.bank_transactional_ms.transaction.infrastructure.dto.TransactionReportDTO;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service("TransactionReportUseCase")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class TransactionReportUseCaseImpl implements QueryUseCase <TransactionReportDTO> {

    TransactionService transactionService;
    SystemPropertiesConfig systemPropertiesConfig;

    @Override
    public TransactionReportDTO executeQuery(TransactionReportDTO transactionReportDTO) {
        var transactionList = transactionService.getAccountAndTransactionsByDateAndIdentificationNumber(
                transactionReportDTO.getStartDate(), transactionReportDTO.getFinalDate(),
                transactionReportDTO.getIdentificationNumber());
        if(transactionList.isEmpty()) {
            throw new EntityNotFoundRuntimeException(null,
                    String.format(systemPropertiesConfig.getMessages().getError().getEntityNotFoundMsj(),
                            transactionReportDTO.getIdentificationNumber()));
        }
        return TransactionReportDTO.builder()
                .startDate(transactionReportDTO.getStartDate())
                .finalDate(transactionReportDTO.getFinalDate())
                .balance(transactionList.get(0).getAccount().getBalance())
                .identificationNumber(transactionReportDTO.getIdentificationNumber())
                .accountNumber(transactionList.get(0).getAccount().getNumber())
                .transactions(
                        transactionList.stream()
                                .map(Transaction::toDTO)
                                .sorted((element1, element2) -> element2.getCreatedAt().compareTo(element1.getCreatedAt()))
                                .toList())
                .build();
    }
}
