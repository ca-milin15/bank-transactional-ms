package com.pichincha.bank_transactional_ms.account.application.usecase;

import com.pichincha.bank_transactional_ms.account.application.dto.AccountDTO;
import com.pichincha.bank_transactional_ms.account.application.transactional.AccountTransactionalService;
import com.pichincha.bank_transactional_ms.shared.application.usecase.CommandUseCase;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service("AccountCreateUseCase")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class AccountCreateUseCaseImpl implements CommandUseCase<AccountDTO> {

    AccountTransactionalService accountTransactionalService;

    @Override
    public AccountDTO executeCommand(AccountDTO accountDTO) {
        var account = accountTransactionalService.createAccount(accountDTO.toAccount());
        return account.toDTO();
    }
}
