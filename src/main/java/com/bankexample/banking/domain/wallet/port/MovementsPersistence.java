package com.bankexample.banking.domain.wallet.port;

import com.bankexample.banking.domain.wallet.data.Movements;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface MovementsPersistence {

    List<Movements> getByWalletId(UUID uuid);

    UUID addMovement(UUID accountId, BigDecimal amount, String currency);
}
