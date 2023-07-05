package com.bankexample.banking.application.rest;

import com.bankexample.banking.application.exceptions.CannotPerformOperation;
import com.bankexample.banking.application.exceptions.NoSuchElementFoundException;
import com.bankexample.banking.application.rest.request.MovementRequest;
import com.bankexample.banking.application.rest.response.EntityIdResponse;
import com.bankexample.banking.application.rest.response.WalletResponse;
import com.bankexample.banking.domain.UserService;
import com.bankexample.banking.domain.WalletService;
import com.bankexample.banking.domain.wallet.data.Wallet;
import com.bankexample.banking.mapper.WalletMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Operations on the wallet of the user
 */
@RestController
@RequestMapping("/api/wallet")
public class BankController {

    private UserService userService;
    private WalletService walletService;

    public BankController(UserService userService, WalletService walletService) {
        this.userService = userService;
        this.walletService = walletService;
    }

    @Operation(summary = "Get the wallet for a exist user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wallet found for the user"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Wallet not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WalletResponse.class))
            )})
    @GetMapping("/{walletId}")
    public WalletResponse wallet(@Parameter(description = "id of wallet to be searched")
                                 @Valid @PathVariable("walletId") final UUID walletId){

        Wallet walletdata = walletService.get(walletId);
        if ( walletdata == null ){
            throw new NoSuchElementFoundException("Wallet not found");
        }

        return WalletMapper.INSTANCE.walletToDtoResponse(walletdata);
    }

    @Operation(summary = "Make a deposit in a wallet of the exist user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Wallet created for the user"),
            @ApiResponse(responseCode = "409", description = "Payload invalid",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EntityIdResponse.class))
            )})
    @PostMapping("/{walletId}/deposit")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityIdResponse deposit(@Parameter(description = "id of wallet to be searched") @PathVariable final UUID walletId,
                                    @RequestBody @Valid final MovementRequest movement){

        Wallet walletdata = walletService.get(walletId);
        if ( walletdata == null ){
            throw new NoSuchElementFoundException("Wallet not found");
        }
        UUID uuidoperation = walletService.operation(walletdata, movement.getAmount());

        return new EntityIdResponse(uuidoperation);
    }

    @Operation(summary = "Make a transfer from wallet to other wallet of the same user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Wallet transfer do"),
            @ApiResponse(responseCode = "409", description = "Payload invalid",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EntityIdResponse.class))
            )})
    @PostMapping("/{walletFromId}/transfer/{walletToId}")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityIdResponse transfer(@Parameter(description = "id of wallet origin") @PathVariable final UUID walletFromId,
                                     @Parameter(description = "id of wallet destination") @PathVariable final UUID walletToId,
                                     @RequestBody @Valid MovementRequest movement){

        Wallet walletorigin = walletService.get(walletFromId);
        if ( walletorigin == null ){
            throw new NoSuchElementFoundException("Wallet origin not found");
        }
        Wallet walletdestination = walletService.get(walletToId);
        if ( walletdestination == null ){
            throw new NoSuchElementFoundException("Wallet destination not found");
        }
        UUID uuid = walletService.transfer(walletorigin, walletdestination, movement.getAmount());
        if ( uuid == null ) {
            throw new CannotPerformOperation("Failed to transfer");
        }
        return new EntityIdResponse(uuid);
    }


}
