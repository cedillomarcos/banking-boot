package com.bankexample.banking.infrastructure.repository;

import com.bankexample.banking.infrastructure.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByUserId(UUID userUUID);

    Wallet findByAccountId(UUID walletUUID);
}
