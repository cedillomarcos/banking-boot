package com.bankexample.banking.infrastructure.repository;

import com.bankexample.banking.infrastructure.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Wallet, Long> {

}
