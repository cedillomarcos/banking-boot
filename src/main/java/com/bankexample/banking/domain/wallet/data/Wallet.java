package com.bankexample.banking.domain.wallet.data;

import com.bankexample.banking.domain.users.data.User;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Wallet data information
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Wallet {
    private UUID accountId;
    private User user;
    private BigDecimal balance;
    private String currency;
    private List<Movements> movements;
}
