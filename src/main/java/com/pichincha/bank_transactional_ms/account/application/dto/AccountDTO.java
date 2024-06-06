package com.pichincha.bank_transactional_ms.account.application.dto;

import com.pichincha.bank_transactional_ms.account.domain.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AccountDTO {

    String customerIdentification;
    BigInteger number;
    String type;
    BigDecimal initialAmount;
    BigDecimal balance;
    boolean status;

    public Account toAccount() {
        return Account.builder()
                .customerIdentification(customerIdentification)
                .number(number)
                .type(type)
                .initialAmount(initialAmount)
                .status(status)
                .balance(balance)
                .build();
    }
}
