package com.bankexample.banking.application.rest;

import com.bankexample.banking.application.rest.request.MovementRequest;
import com.bankexample.banking.application.rest.response.WalletResponse;
import com.bankexample.banking.domain.UserService;
import com.bankexample.banking.domain.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Operations on the wallet of the user
 */
@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class BankController {

    private UserService userService;
    private WalletService walletService;

    public BankController(UserService userService, WalletService walletService) {
        this.userService = userService;
        this.walletService = walletService;
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<String> deposit(@Valid @PathVariable UUID walletId,
                                          @RequestBody @Valid MovementRequest movement){
        return null;
    }

    @GetMapping("/{walletFromId}/transfer/{walletToId}")
    public ResponseEntity<String> transfer(@Valid @PathVariable UUID walletFromId,
                                           @Valid @PathVariable UUID walletToId,
                                           @RequestBody @Valid MovementRequest movement){
        return null;
    }


}
