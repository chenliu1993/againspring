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
        User expectedUser = new User();
        expectedUser.setId(Long.valueOf("1"));
        expectedUser.setName("root");
        expectedUser.setPassword("root");

        // stored user
        User actualUser = userMapper.findOne(expectedUser.getName());
        assertThat(actualUser.getId()).isEqualTo(expectedUser.getId());
        assertThat(actualUser.getName()).isEqualTo(expectedUser.getName());
        assertThat(actualUser.getPassword()).isEqualTo(expectedUser.getPassword());
    }
}
