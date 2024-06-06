package com.pichincha.bank_transactional_ms.transaction.domain.enums;

import com.pichincha.bank_transactional_ms.transaction.application.exceptions.BusinessRulesRuntimeException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TransactionTypeEnum {

    DEBITO("DEB", "Débito"),
    CREDITO("CRE", "Crédito");

    String code;
    String description;

    public static TransactionTypeEnum getEnumByCode(String code, String errorCode, String errorMsj) {
        return Arrays.stream(TransactionTypeEnum.values())
                .filter(transactionTypeEnum -> transactionTypeEnum.getCode().equals(code))
                .findFirst().orElseThrow(() -> new BusinessRulesRuntimeException(errorCode, errorMsj));
    }
}
