package com.bankexample.banking.infrastructure.repository;

import com.bankexample.banking.infrastructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    User findByUserId(UUID userId);

    Optional<User> findByLogin(String login);
}
