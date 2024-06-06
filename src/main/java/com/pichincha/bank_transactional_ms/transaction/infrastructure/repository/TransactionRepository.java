package com.pichincha.bank_transactional_ms.transaction.infrastructure.repository;

import com.pichincha.bank_transactional_ms.transaction.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, BigInteger>{

    List<Transaction> findByCreatedAtBetweenAndAccountCustomerIdentification(
            LocalDateTime startDate, LocalDateTime finalDate, String identificationNumber);
}
