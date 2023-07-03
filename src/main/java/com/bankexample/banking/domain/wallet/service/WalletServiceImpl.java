package com.bankexample.banking.domain.wallet.service;

import com.bankexample.banking.domain.WalletService;
import com.bankexample.banking.domain.users.data.User;
import com.bankexample.banking.domain.wallet.data.Wallet;
import com.bankexample.banking.infrastructure.repository.WalletRepository;
import com.bankexample.banking.mapper.WalletMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {

    WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository){
        this.walletRepository = walletRepository;
    }

    @Override
    public UUID create(User user) {
        Assert.noNullElements(List.of(user), "User cannot be null");

        Wallet wallet = Wallet.builder()
                            .user(user)
                            .accountId(UUID.randomUUID())
                            .balance(new BigDecimal(0))
                            .currency("BKP")
                            .build();

        com.bankexample.banking.infrastructure.entity.Wallet walletentity = WalletMapper.INSTANCE.walletToEntity(wallet);
        walletRepository.save(walletentity);
        return walletentity.getAccountId();
    }

    @Override
    public UUID operation(Wallet wallet, BigDecimal amount) {
        return null;
    }
}
