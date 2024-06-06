package com.pichincha.bank_transactional_ms.transaction.application.transactional;

import com.pichincha.bank_transactional_ms.transaction.domain.Transaction;
import com.pichincha.bank_transactional_ms.transaction.infrastructure.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class TransactionServiceImpl implements TransactionService{

    TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        try {
            return transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new RuntimeException(e); // TODO crear excepcion personalizada
        }
    }
}
