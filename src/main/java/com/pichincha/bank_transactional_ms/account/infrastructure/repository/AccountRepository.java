package com.pichincha.bank_transactional_ms.account.infrastructure.repository;

import com.pichincha.bank_transactional_ms.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, BigInteger> {

    Optional<Account> findByCustomerIdentification(String customerIdentification);
}
