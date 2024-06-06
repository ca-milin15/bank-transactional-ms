package com.pichincha.bank_transactional_ms.shared.infrastructure.listener;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ListenerService {

    void executeSubscriber(String message) throws JsonProcessingException;
}
