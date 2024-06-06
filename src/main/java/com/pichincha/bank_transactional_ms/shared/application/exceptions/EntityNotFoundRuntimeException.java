package com.pichincha.bank_transactional_ms.shared.application.exceptions;


public class EntityNotFoundRuntimeException extends RuntimeException {

    String code;
    public EntityNotFoundRuntimeException(String code, String message) {
        super(message);
        this.code= code;
    }
}
