package com.pichincha.bank_transactional_ms.transaction.application.transactional;

import com.pichincha.bank_transactional_ms.transaction.domain.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {

    Transaction createTransaction(Transaction transaction);

    List<Transaction> getAccountAndTransactionsByDateAndIdentificationNumber(LocalDateTime startDate,
                                                                             LocalDateTime finalDate, String identificationNumber);
}
