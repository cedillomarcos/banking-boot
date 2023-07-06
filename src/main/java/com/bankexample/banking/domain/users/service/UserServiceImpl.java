package com.bankexample.banking.domain.users.service;

import com.bankexample.banking.application.exceptions.ConflictAlreadyExistsException;
import com.bankexample.banking.domain.UserService;
import com.bankexample.banking.domain.users.data.User;
import com.bankexample.banking.domain.users.port.UsersPersistence;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    UsersPersistence usersPersistence;

    public UserServiceImpl(UsersPersistence usersPersistence) {
        this.usersPersistence = usersPersistence;
    }

    @Override
    public User getUser(UUID uuid) {
        return usersPersistence.findByUserId(uuid);
    }

    @Override
    public UUID create(final User user) {
        User userf = usersPersistence.findByLogin(user.getLogin());
        if ( userf != null )
            throw new ConflictAlreadyExistsException("User yet exist");

        return usersPersistence.addUser(user);
    }

}
