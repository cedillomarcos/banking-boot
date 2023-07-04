package com.bankexample.banking.application.rest;

import com.bankexample.banking.application.exceptions.NoSuchElementFoundException;
import com.bankexample.banking.application.rest.request.UserRequest;
import com.bankexample.banking.domain.WalletService;
import com.bankexample.banking.mapper.UserMapper;
import com.bankexample.banking.application.rest.response.EntityIdResponse;
import com.bankexample.banking.domain.UserService;
import com.bankexample.banking.domain.users.data.User;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@OpenAPIDefinition
@RestController
@RequestMapping("/api/management")
public class ManagementController {

    private UserService userService;
    private WalletService walletService;

    public ManagementController(UserService userService, WalletService walletService) {
        this.userService = userService;
        this.walletService = walletService;
    }

    @Operation(summary = "Create a wallet for a exist user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Wallet created for the user"),
            @ApiResponse(responseCode = "409", description = "Payload invalid",
                    content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = EntityIdResponse.class))
                        )})
    @PostMapping("/user/{uuid}/wallet")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityIdResponse wallet(@Parameter(description = "UUID of the user") @Valid @PathVariable UUID uuid){

        User user = userService.getUser(uuid);
        if ( user == null ){
            throw new NoSuchElementFoundException("User not found");
        }
        UUID uuidwallet = walletService.create(user);
        return new EntityIdResponse(uuidwallet);
    }

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRequest.class)) }),
            @ApiResponse(responseCode = "409", description = "Payload invalid",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EntityIdResponse.class))
            )})
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityIdResponse user(@Valid @RequestBody UserRequest userdto){

        User userdata = UserMapper.INSTANCE.userRequestToUser(userdto);
        UUID uuid = userService.create(userdata);

        return new EntityIdResponse(uuid);
    }


    @ExceptionHandler(NoSuchElementFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoSuchElementFoundException(
            NoSuchElementFoundException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
