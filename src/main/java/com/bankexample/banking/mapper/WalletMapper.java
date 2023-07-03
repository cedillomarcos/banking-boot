package com.bankexample.banking.mapper;

import com.bankexample.banking.infrastructure.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletMapper {

    WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);

    @Mapping(target = "userID", source = "user.userId")
    Wallet walletToEntity(com.bankexample.banking.domain.wallet.data.Wallet wallet);
}
