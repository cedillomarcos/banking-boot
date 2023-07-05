package com.bankexample.banking.infrastructure.adapter;

import com.bankexample.banking.domain.wallet.data.Wallet;
import com.bankexample.banking.domain.wallet.port.WalletPersistence;
import com.bankexample.banking.infrastructure.repository.WalletRepository;
import com.bankexample.banking.mapper.WalletMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletJpaAdapter implements WalletPersistence {

    WalletRepository walletRepository;

    public WalletJpaAdapter(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public UUID addWallet(Wallet wallet) {
        com.bankexample.banking.infrastructure.entity.Wallet walletentity = WalletMapper.INSTANCE.walletToEntity(wallet);
        walletRepository.save(walletentity);
        return walletentity.getAccountId();
    }

    @Override
    public Wallet findByUserId(UUID userUUID) {
        com.bankexample.banking.infrastructure.entity.Wallet wallet =walletRepository.findByUserId(userUUID);
        return WalletMapper.INSTANCE.walletToDomain(wallet);
    }

    @Override
    public Wallet findByAccountId(UUID walletUUID) {
        com.bankexample.banking.infrastructure.entity.Wallet wallet = walletRepository.findByAccountId(walletUUID);
        return WalletMapper.INSTANCE.walletToDomain(wallet);
    }

    @Override
    public Wallet updateBalance(UUID walletUUID, BigDecimal amount) {
        com.bankexample.banking.infrastructure.entity.Wallet wallet = walletRepository.findByAccountId(walletUUID);
        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);
        return WalletMapper.INSTANCE.walletToDomain(wallet);
    }
}
