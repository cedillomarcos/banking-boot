package com.bankexample.banking.domain.wallet.data;

import com.bankexample.banking.domain.users.data.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Wallet data information
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet {
    private UUID accountId;
    private User user;
    private BigDecimal balance;
    private String currency;
    private List<Movements> movements;
}
