package com.pichincha.bank_transactional_ms.shared.infrastructure.listener.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ErrorOut {
    String code;
    String message;
}
