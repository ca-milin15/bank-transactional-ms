package com.pichincha.bank_transactional_ms.transaction.domain;

import com.pichincha.bank_transactional_ms.account.domain.Account;
import com.pichincha.bank_transactional_ms.transaction.application.dto.TransactionDTO;
import com.pichincha.bank_transactional_ms.transaction.domain.enums.TransactionTypeConverter;
import com.pichincha.bank_transactional_ms.transaction.domain.enums.TransactionTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "tbl_transaction")
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Transaction {

    @Id
    @SequenceGenerator(name = "transaction_sequence", sequenceName = "tbl_transaction_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence")
    BigInteger id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id")
    Account account;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Convert(converter = TransactionTypeConverter.class)
    TransactionTypeEnum type;

    BigDecimal amount;
    BigDecimal balance;

    public TransactionDTO toDTO() {
        return TransactionDTO.builder()
                .id(id)
                .type(type.getDescription())
                .balance(balance)
                .amount(amount)
                .createdAt(createdAt)
                .build();
    }
}
