package com.bankexample.banking.domain.wallet.data;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class Movements {
    private UUID movementID;
    private MovementType type;
    private BigDecimal amount;
    private String currency;
    private OffsetDateTime createdAt;
}
