package com.bankexample.banking.infrastructure.repository;

import com.bankexample.banking.infrastructure.entity.Movements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovementsRepository extends JpaRepository<Movements, Long> {

    List<Movements> getByWalletId(UUID uuid);
}
