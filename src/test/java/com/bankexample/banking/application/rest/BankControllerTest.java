package com.bankexample.banking.application.rest;

import com.bankexample.banking.domain.UserService;
import com.bankexample.banking.domain.WalletService;
import com.bankexample.banking.domain.wallet.data.Wallet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = com.bankexample.banking.application.rest.BankController.class)
public class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private WalletService walletService;

    @BeforeEach
    void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Wallet wallet = objectMapper.readValue(new File("src/test/resources/worldspec/wallet.json"), Wallet.class);
        Mockito.when(walletService.get(Mockito.eq(UUID.fromString("5e5a0f42-3183-433c-adc8-13feea969db0")))).thenReturn(wallet);
        Mockito.when(walletService.get(Mockito.eq(UUID.fromString("94c8fe60-9f83-4e7a-bf5d-e714f4ede965")))).thenReturn(wallet);
        Mockito.when(walletService.operation(Mockito.any(), Mockito.any())).thenReturn(UUID.randomUUID());
        Mockito.when(walletService.transfer(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(UUID.randomUUID());
    }


    @Test
    public void get_wallet_byuuid_expected_ok_and_return_wallet() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                        "/api/wallet/5e5a0f42-3183-433c-adc8-13feea969db0")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movements", hasSize(5)));
    }

    @Test
    public void get_wallet_byfakeuuid_expected_404_and_no_return() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                        "/api/wallet/111111111-1111-1111-1111-11111111111")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void post_deposit_in_wallet_expected_202_and_return() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/wallet/5e5a0f42-3183-433c-adc8-13feea969db0/deposit")
                .content("{\"amount\": 1000, \"currency\": \"BKP\", \"operation\":\"DEPOSIT\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void post_transfer_wallet_expected_202_and_return() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/wallet/5e5a0f42-3183-433c-adc8-13feea969db0/transfer/94c8fe60-9f83-4e7a-bf5d-e714f4ede965")
                .content("{\"amount\": 1000, \"currency\": \"BKP\", \"operation\":\"TRANSFER\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andDo(print());
    }

}
