package com.nibss.eazibank.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private AccountController accountController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void accountControllerExistTest() {
        AccountController accountController = new AccountController();
        assertNotNull(accountController);
    }
    @Test
    public void contextLoads() throws Exception {
        assertThat(accountController).isNotNull();
    }

    @Test
    public void mvcBeanLoads() throws Exception {

        assertThat(mockMvc).isNotNull();
    }

    @Test
    public void accountControllerCanBeLocatedTest() {
//        mockMvc.perform(get("api/v1/"))
//                .andExpect(status().isOk());
    }
}