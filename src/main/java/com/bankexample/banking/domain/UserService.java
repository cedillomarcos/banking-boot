package com.bankexample.banking.domain;

import com.bankexample.banking.domain.users.data.User;

import java.util.UUID;

public interface UserService {

    User getUser(UUID uuid);

    UUID create(User user);

    boolean exist(UUID uuid);
}
