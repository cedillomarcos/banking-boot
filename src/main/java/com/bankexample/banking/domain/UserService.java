package com.bankexample.banking.domain;

import com.bankexample.banking.domain.users.data.User;

import java.util.UUID;

public interface UserService {

    User getUser(UUID uuid);

    User create(User user);

    boolean exist(UUID uuid);
}
