package com.nibss.eazibank.controller;

import com.nibss.eazibank.account.controller.AccountController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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