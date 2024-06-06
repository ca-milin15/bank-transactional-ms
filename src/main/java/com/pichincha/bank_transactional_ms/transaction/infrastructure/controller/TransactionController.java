package com.pichincha.bank_transactional_ms.transaction.infrastructure.controller;

import com.pichincha.bank_transactional_ms.shared.application.usecase.CommandUseCase;
import com.pichincha.bank_transactional_ms.transaction.application.dto.TransactionDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
@AllArgsConstructor
public class TransactionController {

    @Qualifier("TransactionCreateUseCase")
    CommandUseCase<TransactionDTO> transactionServiceUseCase;

    @PostMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public TransactionDTO transactionCreateProcess(@RequestBody @Validated TransactionDTO customerDTO){
        return transactionServiceUseCase.executeCommand(customerDTO);
    }
}
