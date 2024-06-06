package com.pichincha.bank_transactional_ms.shared.infrastructure.listener.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CustomerDTO {

    BigInteger id;
    String password;
    boolean status;
    String name;
    String gender;
    int age;
    String identification;
    String address;
    String phone;
}
