package com.bankexample.banking.domain.wallet.port;


import com.bankexample.banking.domain.wallet.data.Wallet;

import java.math.BigDecimal;
import java.util.UUID;

public interface WalletPersistence {

    UUID addWallet(Wallet wallet);

    Wallet findByUserId(UUID userUUID);

    Wallet findByAccountId(UUID walletUUID);

    Wallet updateBalance(Wallet wallet, BigDecimal amount);
}
