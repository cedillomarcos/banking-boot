package com.bankexample.banking.infrastructure.entity;

import com.bankexample.banking.domain.wallet.data.MovementType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.UUID;

@Entity(name="movements")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID movementId;
    private UUID walletId;
    private BigDecimal amount;
    private MovementType type;
    private String currency;

    @CreationTimestamp
    private LocalTime createAt;
}
