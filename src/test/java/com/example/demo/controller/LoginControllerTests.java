package com.example.demo.controller;

import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

// import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.TestPropertySource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
// @TestPropertySource(value = { "classpath:application.properties" })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void TestTranslate() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/");
        mvc.perform(request).andDo(print()).andExpect(status().is3xxRedirection()).andReturn();
        // TODO: is there a better way to decides if we redierct to the right page?
        /*
         * assertEquals(true, result.getResponse().getContentAsString()
         * .contains("ユーザーログインデモ"));
         */
    }

    @Test
    void TestLoginSuccess() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/login-success");
        mvc.perform(request).andDo(print()).andExpect(status().is3xxRedirection()).andReturn();
        /*
         * assertEquals(true, result.getResponse().getContentAsString()
         * .contains("/login-success"));
         */
    }

    @Test
    void TestLoginWithKnownUser() throws Exception {
        // Fake one user input
        // if known user should redirect to login-success
        mvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("name", "root")
                .param("password", "root")).andDo(print()).andExpect(status().is3xxRedirection());
    }

    /*
     * @Test
     * void TestLoginWithUnKnownUser() throws Exception {
     * // Fake one user input
     * // if known user should just 200
     * mvc.perform(MockMvcRequestBuilders.post("/login")
     * .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
     * .param("name", "test")
     * .param("password", "test")).andDo(print()).andExpect(status().isOk());
     * }
     */

    @Test
    void TestDelete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/index/root")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)).andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void TestRegister() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/register")
                .param("name", "test")
                .param("password", "test")
                .param("role", "admin")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)).andDo(print())
                .andExpect(status().isOk());
    }

}
