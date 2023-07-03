package com.bankexample.banking.application.rest;

import com.bankexample.banking.application.request.MovementRequestDto;
import com.bankexample.banking.application.response.WalletResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Operations bussines login on the wallet of the user
 */
@RestController
@RequestMapping("/api/wallet")
public class BankController {


    @GetMapping("/me")
    public ResponseEntity<WalletResponseDto> getMe(){
        return null;
    }

    @GetMapping
    public ResponseEntity<WalletResponseDto> get(){
        return null;
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<String> deposit(@Valid @PathVariable UUID walletId,
                                          @RequestBody @Valid MovementRequestDto movement){
        return null;
    }

    @GetMapping("/{walletFromId}/transfer/{walletToId}")
    public ResponseEntity<String> transfer(@Valid @PathVariable UUID walletFromId,
                                           @Valid @PathVariable UUID walletToId,
                                           @RequestBody @Valid MovementRequestDto movement){
        return null;
    }


}
