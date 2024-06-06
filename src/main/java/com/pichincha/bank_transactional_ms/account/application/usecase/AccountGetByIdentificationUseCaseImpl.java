package com.pichincha.bank_transactional_ms.account.application.usecase;

import com.pichincha.bank_transactional_ms.account.application.dto.AccountDTO;
import com.pichincha.bank_transactional_ms.account.application.transactional.AccountTransactionalService;
import com.pichincha.bank_transactional_ms.account.domain.Account;
import com.pichincha.bank_transactional_ms.shared.application.usecase.QueryUseCase;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service("AccountGetByIdentificationUseCase")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class AccountGetByIdentificationUseCaseImpl implements QueryUseCase<Account> {

    AccountTransactionalService accountTransactionalService;

    @Override
    public Account executeQuery(Account accountDTO) {
        return accountTransactionalService.getAccountByIdentification(accountDTO.getCustomerIdentification())
                .orElseThrow(() -> new RuntimeException(""));
    }
}
