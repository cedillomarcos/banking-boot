package com.bankexample.banking.domain.wallet.data;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class Wallet {
    public UUID accountId;
    private UUID userID;
    private BigDecimal balance;
    private String currency;

    private List<Movements> movements;
}
