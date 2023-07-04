package com.bankexample.banking.application.rest;

import com.bankexample.banking.application.rest.request.AuthRequest;
import com.bankexample.banking.application.rest.response.JWTDataToken;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@OpenAPIDefinition
@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping("login")
    public ResponseEntity<JWTDataToken> login(@RequestBody @Validated AuthRequest request){
        return null;
    }
}
