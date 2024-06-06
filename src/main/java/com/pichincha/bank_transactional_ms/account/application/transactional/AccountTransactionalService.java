package com.pichincha.bank_transactional_ms.account.application.transactional;

import com.pichincha.bank_transactional_ms.account.domain.Account;

import java.util.Optional;

public interface AccountTransactionalService {

    Account createAccount(Account account);

    Optional<Account> getAccountByIdentification(String identification);
}
