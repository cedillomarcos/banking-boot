package com.bankexample.banking.domain.wallet.service;

import com.bankexample.banking.application.exceptions.NoSuchElementFoundException;
import com.bankexample.banking.domain.WalletService;
import com.bankexample.banking.domain.users.data.User;
import com.bankexample.banking.domain.wallet.data.MovementType;
import com.bankexample.banking.domain.wallet.data.Wallet;
import com.bankexample.banking.infrastructure.entity.Movements;
import com.bankexample.banking.infrastructure.repository.MovementsRepository;
import com.bankexample.banking.infrastructure.repository.WalletRepository;
import com.bankexample.banking.mapper.WalletMapper;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class WalletServiceImpl implements WalletService {

    WalletRepository walletRepository;
    MovementsRepository movementsRepository;

    public WalletServiceImpl(WalletRepository walletRepository, MovementsRepository movementsRepository) {
        this.walletRepository = walletRepository;
        this.movementsRepository = movementsRepository;
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
    public Wallet get(UUID uuid) {
        Assert.noNullElements(List.of(uuid), "Wallet cannot be null");
        com.bankexample.banking.infrastructure.entity.Wallet wallet = walletRepository.findByAccountId(uuid);
        if (wallet == null){
          return null;
        }
        List<Movements> lstmovs = movementsRepository.getByWalletId(wallet.getAccountId());

        Wallet walletdomain = WalletMapper.INSTANCE.walletToDomain(wallet);
        List<com.bankexample.banking.domain.wallet.data.Movements> lstmovsdata = WalletMapper.INSTANCE.movementsToDomain(lstmovs);
        walletdomain.setMovements(lstmovsdata);

        return walletdomain;
    }

    @Override
    public UUID operation(Wallet wallet, BigDecimal amount) {
        com.bankexample.banking.infrastructure.entity.Wallet wallentity = walletRepository.findByAccountId(wallet.getAccountId());

        if (wallentity != null){
            Movements mov = Movements.builder()
                                    .movementId(UUID.randomUUID())
                                    .walletId(wallet.getAccountId())
                                    .currency("BKP")
                                    .type(amount.signum() >= 0 ? MovementType.IN : MovementType.OUT)
                                    .amount(amount)
                                    .build();
            movementsRepository.save(mov);
            wallentity.setBalance(wallentity.getBalance().add(amount));
            walletRepository.save(wallentity);

            return mov.getMovementId();
        }
        return null;
    }

    @Override
    public UUID transfer(Wallet walletOrig, Wallet walletDest, BigDecimal amount) {
        com.bankexample.banking.infrastructure.entity.Wallet walletA = walletRepository.findByAccountId(walletOrig.getAccountId());
        com.bankexample.banking.infrastructure.entity.Wallet walletB = walletRepository.findByAccountId(walletDest.getAccountId());

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
