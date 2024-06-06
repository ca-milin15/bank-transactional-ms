package com.pichincha.bank_transactional_ms.transaction.infrastructure.controller;

import com.pichincha.bank_transactional_ms.shared.application.usecase.CommandUseCase;
import com.pichincha.bank_transactional_ms.shared.application.usecase.QueryUseCase;
import com.pichincha.bank_transactional_ms.transaction.application.dto.TransactionDTO;
import com.pichincha.bank_transactional_ms.transaction.infrastructure.dto.TransactionReportDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.*;

@RestController
@RequestMapping("/movimientos")
@AllArgsConstructor
public class TransactionController {

    @Qualifier("TransactionCreateUseCase")
    CommandUseCase<TransactionDTO> transactionServiceUseCase;

    @Qualifier("TransactionReportUseCase")
    QueryUseCase<TransactionReportDTO> transactionReportUseCase;

    @PostMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public TransactionDTO transactionCreateProcess(@RequestBody @Validated TransactionDTO customerDTO){
        return transactionServiceUseCase.executeCommand(customerDTO);
    }

    @GetMapping("/reportes")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public TransactionReportDTO generateReport(
            @RequestParam(required = true) String startDate,
            @RequestParam(required = true) String finalDate,
            @RequestParam(required = true) String identificationNumber){
        var transactionReportDTO = TransactionReportDTO.builder()
                .startDate(LocalDateTime.of(LocalDate.parse(startDate), LocalTime.of(0, 0)))
                .finalDate(LocalDateTime.of(LocalDate.parse(finalDate), LocalTime.of(23, 59)))
                .identificationNumber(identificationNumber)
                .build();
        return transactionReportUseCase.executeQuery(transactionReportDTO);
    }
}
