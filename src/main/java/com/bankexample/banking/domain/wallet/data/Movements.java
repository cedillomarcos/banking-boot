package com.bankexample.banking.domain.wallet.data;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movements {
    private UUID movementID;
    private MovementType type;
    private BigDecimal amount;
    private String currency;
    private OffsetDateTime createdAt;
}
