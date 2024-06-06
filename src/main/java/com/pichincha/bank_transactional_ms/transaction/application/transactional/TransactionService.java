package com.pichincha.bank_transactional_ms.transaction.application.transactional;

import com.pichincha.bank_transactional_ms.transaction.domain.Transaction;

public interface TransactionService {

    Transaction createTransaction(Transaction transaction);
}
