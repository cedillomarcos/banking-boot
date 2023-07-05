package com.bankexample.banking.domain;

import com.bankexample.banking.infrastructure.entity.User;
import com.bankexample.banking.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UsersServiceDomainTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() throws Exception {
        User user = User.builder().userId(UUID.fromString("40185c59-12d0-409d-9d13-888e8b32c7c4"))
                        .name("Marcos")
                        .surname("Martinez")
                        .build();

        Mockito.when(userRepository.findByUserId(user.getUserId()))
                .thenReturn(user);
    }

    @Test
    public void when_user_send_then_created_expected_ok() throws Exception {
        com.bankexample.banking.domain.users.data.User user = userService.getUser(UUID.fromString("40185c59-12d0-409d-9d13-888e8b32c7c4"));
        Assert.isTrue(user.getName().equals("Marcos"));
    }
}
