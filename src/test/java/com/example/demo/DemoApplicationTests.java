package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// import com.example.demo.controller.LoginController;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.aop.interceptor.ExposeInvocationInterceptor;

// @WebMvcTest(LoginController.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {
    }

    @Test
    void loginTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/login");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals(true, result.getResponse().getContentAsString().contains("ユーザーログインデモ"));
    }

}
