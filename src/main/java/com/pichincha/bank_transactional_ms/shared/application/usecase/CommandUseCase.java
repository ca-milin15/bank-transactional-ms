package com.pichincha.bank_transactional_ms.shared.application.usecase;

public interface CommandUseCase <T> {

    T executeCommand(T command);
}
