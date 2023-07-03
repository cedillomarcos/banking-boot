package com.bankexample.banking.application.response;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDto {
    private UUID id;
    private String name;
    private String surname;
    private UUID walletId;
}
