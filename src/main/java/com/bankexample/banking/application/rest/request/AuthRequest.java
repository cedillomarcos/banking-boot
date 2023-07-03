package com.bankexample.banking.application.rest.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
