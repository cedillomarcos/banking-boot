package com.bankexample.banking.domain.users.service;

import com.bankexample.banking.application.exceptions.ConflictAlreadyExistsException;
import com.bankexample.banking.domain.UserService;
import com.bankexample.banking.infrastructure.entity.User;
import com.bankexample.banking.infrastructure.repository.UserRepository;
import com.bankexample.banking.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public com.bankexample.banking.domain.users.data.User getUser(UUID uuid) {

        User userentity = userRepository.findByUserId(uuid);

        return UserMapper.INSTANCE.entityToDomain(userentity);
    }

    @Override
    public UUID create(com.bankexample.banking.domain.users.data.User user) {

        Optional<User> userentity = userRepository.findByLogin(user.getLogin());
        if ( userentity.isPresent() )
            throw new ConflictAlreadyExistsException("User yet exist");

        User userToSave = UserMapper.INSTANCE.userDomainToEntity(user);
        userToSave.setUserId(UUID.randomUUID());
        userToSave = userRepository.save(userToSave);
        return userToSave.getUserId();
    }

}
