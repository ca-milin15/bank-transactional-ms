package com.pichincha.bank_transactional_ms.account.application.transactional;

import com.pichincha.bank_transactional_ms.account.domain.Account;
import com.pichincha.bank_transactional_ms.account.infrastructure.repository.AccountRepository;
import com.pichincha.bank_transactional_ms.shared.application.exceptions.EntityNotFoundRuntimeException;
import com.pichincha.bank_transactional_ms.shared.infrastructure.config.SystemPropertiesConfig;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AccountTransactionalServiceImpl implements AccountTransactionalService {

    AccountRepository accountRepository;
    SystemPropertiesConfig systemPropertiesConfig;

    @Override
    public Account createAccount(Account account) {
        try {
            var accountSeek = getAccountByIdentification(account.getCustomerIdentification());
            return accountSeek.orElseGet(() -> accountRepository.save(account));
        } catch (Exception e) {
            throw new EntityNotFoundRuntimeException(null,
                    String.format(systemPropertiesConfig.getMessages().getError().getEntityNotFoundMsj(),
                            account.getCustomerIdentification())
            );
        }
    }

    @Override
    public Optional<Account> getAccountByIdentification(String identification) {
        return accountRepository.findByCustomerIdentification(identification);
    }
}
