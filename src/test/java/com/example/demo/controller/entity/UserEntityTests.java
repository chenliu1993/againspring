package com.example.demo.controller.entity;

import org.junit.jupiter.api.Test;

import com.example.demo.domain.UserEntity;
import static org.assertj.core.api.Assertions.assertThat;

public class UserEntityTests {
    @Test
    void TestUser() throws Exception {
        UserEntity user = new UserEntity();
        user.setName("test");
        user.setPassword("test");
        user.setRole("developer");

        // stored user
        assertThat(user.getRole()).isEqualTo("developer");
        assertThat(user.getName()).isEqualTo("test");
        assertThat(user.getPassword()).isEqualTo("test");
    }
}
