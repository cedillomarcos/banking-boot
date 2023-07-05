package com.bankexample.banking.mapper;

import com.bankexample.banking.application.rest.response.MovementsResponse;
import com.bankexample.banking.application.rest.response.WalletResponse;
import com.bankexample.banking.domain.wallet.data.Movements;
import com.bankexample.banking.infrastructure.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WalletMapper {

    WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);

    @Mapping(target = "userId", source = "user.userId")
    Wallet walletToEntity(com.bankexample.banking.domain.wallet.data.Wallet wallet);

    @Mapping(target = "user.userId", source = "userId")
    com.bankexample.banking.domain.wallet.data.Wallet walletToDomain(Wallet wallet);

    Movements movementsToDomain(com.bankexample.banking.infrastructure.entity.Movements movements);

    List<Movements> movementsToDomain(List<com.bankexample.banking.infrastructure.entity.Movements> movements);

    WalletResponse walletToDtoResponse(com.bankexample.banking.domain.wallet.data.Wallet wallet);

    MovementsResponse movementsToDtoResponse(Movements movements);

    List<MovementsResponse> movementsToDtoResponse(List<Movements> movements);
}
