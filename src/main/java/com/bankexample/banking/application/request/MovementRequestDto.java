package com.bankexample.banking.application.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MovementRequestDto {
    private BigDecimal amount;
    private String currency;
    private Operation operation;
}
