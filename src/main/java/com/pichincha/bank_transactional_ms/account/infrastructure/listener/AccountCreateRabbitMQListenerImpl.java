package com.pichincha.bank_transactional_ms.account.infrastructure.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pichincha.bank_transactional_ms.account.application.dto.AccountDTO;
import com.pichincha.bank_transactional_ms.shared.application.usecase.CommandUseCase;
import com.pichincha.bank_transactional_ms.shared.infrastructure.listener.RabbitMQListenerService;
import com.pichincha.bank_transactional_ms.shared.infrastructure.listener.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AccountCreateRabbitMQListenerImpl implements RabbitMQListenerService {

    @Qualifier("AccountCreateUseCase")
    CommandUseCase<AccountDTO> commandUseCase;
    ObjectMapper objectMapper;


    @Override
    @RabbitListener(queues = "bank.queue.new.customer")
    public void executeSubscriber(String message) throws JsonProcessingException {
        var customerCreated = objectMapper.readValue(message, CustomerDTO.class);
        var accountDTO = AccountDTO.builder()
                .customerIdentification(customerCreated.getIdentification())
                .initialAmount(BigDecimal.valueOf(10000))
                .balance(BigDecimal.valueOf(10000))
                .number(BigInteger.valueOf(Long.parseLong(customerCreated.getIdentification())))
                .type("CC")
                .status(true)
                .build();
        commandUseCase.executeCommand(accountDTO);
    }

}
