package com.pichincha.bank_transactional_ms.transaction.domain.enums;

import jakarta.persistence.AttributeConverter;

public class TransactionTypeConverter implements AttributeConverter<TransactionTypeEnum, String>{

    @Override
    public String convertToDatabaseColumn(TransactionTypeEnum transactionTypeEnum) {
        return transactionTypeEnum.getCode();
    }

    @Override
    public TransactionTypeEnum convertToEntityAttribute(String code) {
        return TransactionTypeEnum.getEnumByCode(code, null, null);
    }
}
