package com.bankexample.banking.application.rest.response;

import com.bankexample.banking.domain.wallet.data.MovementType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovementsResponse {

    private UUID movementID;
    private MovementType type;
    private BigDecimal amount;
    private OffsetDateTime createdAt;
    private String currency;

}