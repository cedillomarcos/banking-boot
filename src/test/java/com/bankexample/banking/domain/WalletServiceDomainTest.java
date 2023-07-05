package com.bankexample.banking.domain;

import com.bankexample.banking.domain.wallet.data.Wallet;
import com.bankexample.banking.infrastructure.repository.UserRepository;
import com.bankexample.banking.infrastructure.repository.WalletRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WalletServiceDomainTest {

    @MockBean
    WalletRepository walletRepository;

    @Autowired
    WalletService walletService;

/*
UUID operation(Wallet wallet, BigDecimal amount);

    UUID transfer(Wallet walletOrig, Wallet walletDest, BigDecimal amount);

 */
    @BeforeEach
    void setUp() throws Exception {
        com.bankexample.banking.infrastructure.entity.Wallet wallet = com.bankexample.banking.infrastructure.entity.Wallet.builder()
                .accountId(UUID.randomUUID())
                .balance(new BigDecimal(0))
                .build();

        Mockito.when(walletRepository.save(Mockito.any()))
                .thenReturn(UUID.randomUUID());

        Mockito.when(walletRepository.findByAccountId(Mockito.any())).thenReturn(wallet);
    }


    @Test
    public void user_do_deposit_created_expected_ok() throws Exception {
        //movementsRepository

        Wallet wallet = Wallet.builder()
                            .accountId(UUID.randomUUID())
                            .balance(new BigDecimal(0))
                            .build();

       UUID uuid =  walletService.operation( wallet, new BigDecimal(1000) );
       Assertions.assertNotNull(uuid);
    }


}
