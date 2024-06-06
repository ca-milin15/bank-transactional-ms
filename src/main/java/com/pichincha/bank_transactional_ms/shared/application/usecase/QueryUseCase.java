package com.pichincha.bank_transactional_ms.shared.application.usecase;

public interface QueryUseCase <T>{

    T executeQuery(T command);
}
