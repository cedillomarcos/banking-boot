package com.bankexample.banking.mapper;

import com.bankexample.banking.application.rest.request.UserRequest;
import com.bankexample.banking.domain.users.data.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userRequestToUser(UserRequest user);

    com.bankexample.banking.infrastructure.entity.User userDomainToEntity(User user);

    User entityToDomain(com.bankexample.banking.infrastructure.entity.User user);
}
