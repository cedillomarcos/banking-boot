package com.bankexample.banking.application.response;

import com.bankexample.banking.domain.wallet.data.MovementType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class MovementsResponseDto {

    private UUID movementID;
    private MovementType type;
    private BigDecimal amount;
    private OffsetDateTime createdAt;
    private String currency;
}
