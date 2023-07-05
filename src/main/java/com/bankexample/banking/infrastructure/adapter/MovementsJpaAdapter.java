package com.bankexample.banking.infrastructure.adapter;

import com.bankexample.banking.domain.wallet.data.MovementType;
import com.bankexample.banking.domain.wallet.data.Movements;
import com.bankexample.banking.domain.wallet.port.MovementsPersistence;
import com.bankexample.banking.infrastructure.repository.MovementsRepository;
import com.bankexample.banking.mapper.WalletMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class MovementsJpaAdapter implements MovementsPersistence {

    MovementsRepository movementsRepository;

    public MovementsJpaAdapter(MovementsRepository movementsRepository) {
        this.movementsRepository = movementsRepository;
    }


    @Override
    public List<Movements> getByWalletId(UUID uuid) {
        List<com.bankexample.banking.infrastructure.entity.Movements> lstmovs = movementsRepository.getByWalletId(uuid);
        return WalletMapper.INSTANCE.movementsToDomain(lstmovs);
    }

    @Override
    public UUID addMovement(UUID accountId, BigDecimal amount, String currency) {

        com.bankexample.banking.infrastructure.entity.Movements mov = com.bankexample.banking.infrastructure.entity.Movements.builder()
                .movementId(UUID.randomUUID())
                .walletId(accountId)
                .currency("BKP")
                .type(amount.signum() >= 0 ? MovementType.IN : MovementType.OUT)
                .amount(amount)
                .build();
        movementsRepository.save(mov);

        return mov.getMovementId();
    }
}
