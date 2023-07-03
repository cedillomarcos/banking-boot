package com.bankexample.banking.domain.users.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private UUID userId;
    private String name;
    private String surname;
    private String login;
    private String password;
}
