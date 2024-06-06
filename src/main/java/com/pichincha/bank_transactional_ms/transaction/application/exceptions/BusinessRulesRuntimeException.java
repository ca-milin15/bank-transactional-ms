package com.pichincha.bank_transactional_ms.transaction.application.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class BusinessRulesRuntimeException extends RuntimeException {

    String code;

    public BusinessRulesRuntimeException(String code, String message) {
        super(message);
        this.code = code;
    }
}
