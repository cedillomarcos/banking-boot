package com.bankexample.banking.domain;

import com.bankexample.banking.domain.users.data.User;
import com.bankexample.banking.domain.wallet.data.Wallet;

import java.math.BigDecimal;
import java.util.UUID;

public interface WalletService {

    UUID create(User user);

    Wallet get(UUID uuid);

    UUID operation(Wallet wallet, BigDecimal amount);

    UUID transfer(Wallet walletOrig, Wallet walletDest, BigDecimal amount);

}
