package com.pichincha.bank_transactional_ms.account.domain;

import com.pichincha.bank_transactional_ms.account.application.dto.AccountDTO;
import com.pichincha.bank_transactional_ms.transaction.domain.Transaction;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "tbl_account")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class Account {

    @Id
    @SequenceGenerator(name = "account_sequence", sequenceName = "tbl_account_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    BigInteger id;

    @Column(name = "customer_identification")
    String customerIdentification;

    BigInteger number;
    String type;

    @Column(name = "initial_amount")
    BigDecimal initialAmount;

    BigDecimal balance;
    boolean status;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    List<Transaction> transactions;

    public AccountDTO toDTO() {
        return AccountDTO.builder()
                .customerIdentification(customerIdentification)
                .number(number)
                .type(type)
                .initialAmount(initialAmount)
                .status(status)
                .build();
    }
}
