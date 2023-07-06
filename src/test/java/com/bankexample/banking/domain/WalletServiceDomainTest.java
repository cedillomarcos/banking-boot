package com.bankexample.banking.domain;

import com.bankexample.banking.domain.wallet.data.Wallet;
import com.bankexample.banking.domain.wallet.port.WalletPersistence;
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
    WalletPersistence walletPersistence;

    @Autowired
    WalletService walletService;

    Wallet wallet;
    Wallet walletA;

    @BeforeEach
    void setUp() throws Exception {
        UUID randomaccount = UUID.randomUUID();
        wallet = Wallet.builder()
                .accountId(randomaccount)
                .balance(new BigDecimal(0))
                .build();

        walletA = Wallet.builder()
                .accountId(UUID.randomUUID())
                .balance(new BigDecimal(3000))
                .build();

        Mockito.when(walletPersistence.addWallet(Mockito.any()))
                .thenReturn(wallet.getAccountId());

        Mockito.when(walletPersistence.findByAccountId(wallet.getAccountId())).thenReturn(wallet);
        Mockito.when(walletPersistence.findByAccountId(walletA.getAccountId())).thenReturn(walletA);
    }


    @Test
    public void user_do_deposit_created_expected_ok() throws Exception {
       UUID uuid =  walletService.operation( wallet, new BigDecimal(1000) );
       Assertions.assertNotNull(uuid);
       Assertions.assertEquals(new BigDecimal(1000), wallet.getBalance());
    }

    @Test
    public void user_do_transfer_created_expected_ok() throws Exception {
        wallet.setBalance(new BigDecimal(0));
        UUID uuid =  walletService.transfer( walletA, wallet, new BigDecimal(2000) );
        Assertions.assertNotNull(uuid);
        Assertions.assertEquals(new BigDecimal(2000), wallet.getBalance());
    }

    @Test
    public void user_do_transfer_origin_less_amount_than_required_expected_null() throws Exception {
        wallet.setBalance(new BigDecimal(0));
        UUID uuid =  walletService.transfer( walletA, wallet, new BigDecimal(20000) );
        Assertions.assertNull(uuid);
    }


}
