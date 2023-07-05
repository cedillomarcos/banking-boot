package com.bankexample.banking.domain.wallet.service;

import com.bankexample.banking.domain.WalletService;
import com.bankexample.banking.domain.users.data.User;
import com.bankexample.banking.domain.wallet.data.MovementType;
import com.bankexample.banking.domain.wallet.data.Movements;
import com.bankexample.banking.domain.wallet.data.Wallet;
import com.bankexample.banking.domain.wallet.port.MovementsPersistence;
import com.bankexample.banking.domain.wallet.port.WalletPersistence;
import com.bankexample.banking.mapper.WalletMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class WalletServiceImpl implements WalletService {

    WalletPersistence walletPersistence;
    MovementsPersistence movementsPersistence;

    public WalletServiceImpl(WalletPersistence walletPersistence, MovementsPersistence movementsPersistence) {
        this.walletPersistence = walletPersistence;
        this.movementsPersistence = movementsPersistence;
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
        return walletPersistence.addWallet(wallet);
    }

    @Override
    public Wallet get(UUID uuid) {
        Assert.noNullElements(List.of(uuid), "Wallet cannot be null");

        Wallet walletdto = walletPersistence.findByAccountId(uuid);
        if (walletdto == null){
          return null;
        }
        List<Movements> lstmovs = movementsPersistence.getByWalletId(walletdto.getAccountId());
        walletdto.setMovements(lstmovs);

        return walletdto;
    }

    @Override
    public UUID operation(Wallet wallet, BigDecimal amount) {
        Wallet walletdto = walletPersistence.findByAccountId(wallet.getAccountId());

        if (walletdto != null){
            UUID uuidmov = movementsPersistence.addMovement(walletdto.getAccountId(), amount, "BKP");
            walletPersistence.updateBalance(walletdto.getAccountId(), amount);
            return uuidmov;
        }
        return null;
    }

    @Override
    public UUID transfer(Wallet walletOrig, Wallet walletDest, BigDecimal amount) {

        Wallet walletA = walletPersistence.findByAccountId(walletOrig.getAccountId());
        Wallet walletB = walletPersistence.findByAccountId(walletDest.getAccountId());

        if ( walletA == null || walletB == null)
            return null;

        if (walletA.getBalance().compareTo(amount) < 0)
            return null;

        log.info("Transfer from wallet {0} to Wallet {1}",walletOrig.getAccountId(), walletDest.getAccountId());
        operation(walletOrig, amount.negate());
        UUID uuid = operation(walletDest, amount);
        log.info("Transfer execute wallet {0} to Wallet {1}, amount {2}",walletOrig.getAccountId(), walletDest.getAccountId(), amount);
        return uuid;
    }
}
