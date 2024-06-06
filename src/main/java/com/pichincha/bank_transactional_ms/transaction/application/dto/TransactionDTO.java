package com.pichincha.bank_transactional_ms.transaction.application.dto;

import com.pichincha.bank_transactional_ms.account.domain.Account;
import com.pichincha.bank_transactional_ms.transaction.domain.Transaction;
import com.pichincha.bank_transactional_ms.transaction.domain.enums.TransactionTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class TransactionDTO {

    BigInteger id;

    @NotBlank
    String customerIdentification;

    @NotBlank
    @Pattern(regexp = "^(DEB|CRE)$")
    String type;

    @NotNull
    BigDecimal amount;

    BigDecimal balance;

    LocalDateTime createdAt;

    public Transaction toTransaction(Account account, TransactionTypeEnum type) {
        var balance = calculateBalance(account, type);
        account.setBalance(balance);
        return Transaction.builder()
                .account(account)
                .type(type)
                .amount(amount)
                .balance(balance)
                .createdAt(LocalDateTime.now())
                .build();
    }

    private BigDecimal calculateBalance(Account account, TransactionTypeEnum type) {
        return switch (type) {
            case DEBITO -> account.getBalance().subtract(amount);
            case CREDITO -> account.getBalance().add(amount);
            default -> null;
        };
    }
}
