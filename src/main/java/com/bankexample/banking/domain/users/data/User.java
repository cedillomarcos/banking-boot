package com.bankexample.banking.domain.users.data;

import lombok.*;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    private UUID userId;
    private String name;
    private String surname;
    private String login;
    private String password;
}
