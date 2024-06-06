package com.pichincha.bank_transactional_ms.shared.infrastructure.config;

import com.pichincha.bank_transactional_ms.shared.infrastructure.listener.dto.ErrorOut;
import com.pichincha.bank_transactional_ms.transaction.application.exceptions.BusinessRulesRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorOut> generalError(RuntimeException ex, WebRequest request){
        var errorOut = new ErrorOut("GenericError", ex.getMessage());
        return new ResponseEntity<>(errorOut, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessRulesRuntimeException.class)
    public ResponseEntity<ErrorOut> businessRulesRuntimeException(BusinessRulesRuntimeException ex, WebRequest request){
        var errorOut = new ErrorOut(ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(errorOut, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorOut> methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){
        var detail = Objects.requireNonNull(ex.getDetailMessageArguments());
        var detailAsString = Arrays.stream(detail).map(String::valueOf).collect(Collectors.joining());
        var errorOut = new ErrorOut(ex.getStatusCode().toString(), detailAsString);
        return new ResponseEntity<>(errorOut, HttpStatus.PRECONDITION_FAILED);
    }


}
