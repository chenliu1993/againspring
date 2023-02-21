package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.mapper.UserMapper;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

// import org.springframework.test.web.servlet.MvcResult;

import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.TestPropertySource;

@AutoConfigureMockMvc
// @TestPropertySource(value = { "classpath:application.properties" })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void TestFindOne() throws Exception {
        User user = new User();
        user.setId(Long.valueOf("1"));
        user.setName("root");
        user.setPassword("root");

        // stored user
        User oldUser = userMapper.findOne(user.getName());
        assertThat(oldUser.getId()).isEqualTo(user.getId());
        assertThat(oldUser.getName()).isEqualTo(user.getName());
        assertThat(oldUser.getPassword()).isEqualTo(user.getPassword());
    }
}
