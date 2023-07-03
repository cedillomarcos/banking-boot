package com.bankexample.banking.application.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletResponse {

    public UUID accountId;
    private BigDecimal balance;
    private List<MovementsResponse> movements;
}
