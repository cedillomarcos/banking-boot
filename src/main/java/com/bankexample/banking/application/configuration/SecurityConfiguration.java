package com.bankexample.banking.application.configuration;
/*
import com.bankexample.banking.application.filter.AuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@Configuration
//@EnableWebSecurity
public class SecurityConfiguration {

    //@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .authorizeHttpRequests((authorize) -> authorize
                    .anyRequest().permitAll()
                    //.anyRequest().authenticated()
                    //.requestMatchers("/login").permitAll()
            )
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(Customizer.withDefaults())
            //.addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .csrf(Customizer.withDefaults())
            .formLogin().disable()
            .httpBasic().disable()
            .logout().disable();
        // @formatter:on
        return http.build();
    }

}
*/