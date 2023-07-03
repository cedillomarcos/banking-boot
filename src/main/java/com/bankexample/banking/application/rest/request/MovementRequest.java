package com.bankexample.banking.application.rest.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovementRequest {
    private BigDecimal amount;
    private String currency;
    private Operation operation;
}
