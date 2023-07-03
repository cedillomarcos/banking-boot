package com.bankexample.banking.application.configuration;
/*
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

public class AuthenticationService {

    public static Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (apiKey == null || !apiKey.equals(HttpHeaders.AUTHORIZATION)) {
            throw new BadCredentialsException("Invalid API Key");
        }

        return new ApiTokenAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}
*/