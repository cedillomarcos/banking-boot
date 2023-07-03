package com.bankexample.banking.application.response;

import lombok.Data;

@Data
public class TokenDto {

    private String accessToken;
    private String refreshToken;

}
