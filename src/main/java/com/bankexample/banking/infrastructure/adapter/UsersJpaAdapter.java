package com.bankexample.banking.infrastructure.adapter;

import com.bankexample.banking.domain.users.data.User;
import com.bankexample.banking.domain.users.port.UsersPersistence;
import com.bankexample.banking.infrastructure.repository.UserRepository;
import com.bankexample.banking.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsersJpaAdapter implements UsersPersistence {

    UserRepository userRepository;

    public UsersJpaAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UUID addUser(User userf) {
        com.bankexample.banking.infrastructure.entity.User userToSave = UserMapper.INSTANCE.userDomainToEntity(userf);
        userToSave.setUserId(UUID.randomUUID());
        userToSave = userRepository.save(userToSave);
        return userToSave.getUserId();
    }

    @Override
    public User findByUserId(UUID uuid) {
        com.bankexample.banking.infrastructure.entity.User userentity = userRepository.findByUserId(uuid);
        return UserMapper.INSTANCE.entityToDomain(userentity);
    }

    @Override
    public User findByLogin(String login) {
        Optional<com.bankexample.banking.infrastructure.entity.User> userentity = userRepository.findByLogin(login);
        if (userentity.isPresent())
            return UserMapper.INSTANCE.entityToDomain(userentity.get());
        return null;
    }
}
