package com.bankexample.banking.mapper;

import com.bankexample.banking.application.request.UserRequestDto;
import com.bankexample.banking.domain.users.data.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userRequestToUser(UserRequestDto user);

    com.bankexample.banking.infrastructure.entity.User userDomainToEntity(com.bankexample.banking.domain.users.data.User user);
}
