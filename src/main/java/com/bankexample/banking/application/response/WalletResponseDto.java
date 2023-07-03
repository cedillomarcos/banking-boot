package com.bankexample.banking.application.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class WalletResponseDto {

    public UUID accountId;
    private BigDecimal balance;
    private List<MovementsResponseDto> movements;
}
