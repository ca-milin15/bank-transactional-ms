package com.pichincha.bank_transactional_ms.transaction.infrastructure.dto;

import com.pichincha.bank_transactional_ms.transaction.application.dto.TransactionDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionReportDTO {

    LocalDateTime startDate;
    LocalDateTime finalDate;
    String identificationNumber;
    BigInteger accountNumber;
    BigDecimal balance;
    List<TransactionDTO> transactions;
}
