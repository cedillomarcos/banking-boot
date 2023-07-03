package com.bankexample.banking.application.rest;

import com.bankexample.banking.mapper.UserMapper;
import com.bankexample.banking.application.request.UserRequestDto;
import com.bankexample.banking.application.response.EntityIdResponseDTO;
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
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@OpenAPIDefinition
@RestController
@RequestMapping("/api/management")
public class ManagementController {

    private UserService userService;

    public ManagementController(UserService userService){
        this.userService = userService;
    }

    @Operation(summary = "Create a wallet for a exist user")
    @PostMapping("/user/{id}/wallet")
    public EntityIdResponseDTO wallet(@Parameter(description = "UUID of the user") @Valid @PathVariable UUID id){

        return null;
    }

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "User created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRequestDto.class)) }),
            @ApiResponse(responseCode = "409", description = "Payload invalid",
                    content = @Content)})
    @PostMapping("/user")
    public EntityIdResponseDTO user(@Valid @RequestBody UserRequestDto userdto){

        User userdata = UserMapper.INSTANCE.userRequestToUser(userdto);
        userService.create(userdata);

        return null;
    }
}
