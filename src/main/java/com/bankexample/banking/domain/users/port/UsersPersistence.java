package com.bankexample.banking.domain.users.port;


import com.bankexample.banking.domain.users.data.User;

import java.util.UUID;

public interface UsersPersistence {

    UUID addUser(User userf);

    User findByUserId(UUID uuid);

    User findByLogin(String login);

}
