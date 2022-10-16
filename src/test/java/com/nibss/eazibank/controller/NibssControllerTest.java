package com.nibss.eazibank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nibss.eazibank.data.models.Customer;
import com.nibss.eazibank.data.repositories.NibssRepository;
import com.nibss.eazibank.services.NibssInterface;
import com.nibss.eazibank.services.NibssInterfaceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NibssController.class)
class NibssControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NibssInterface nibssInterface;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void nibssControllerExistTest() {
        NibssController nibssController = new NibssController();
        assertThat(nibssController).isNotNull();
    }

    @Test
    void whenTestApp_ThenEmptyResponseTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/nibss/is-nibss"))
                .andExpect(status().isOk());
    }

    @Test
    void whenNibssIsCalled_BvnIsGeneratedTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/nibss/bvn-generator"))
                .andExpect(status().isOk());
    }

    @Test
    void whenWrongHttpMethodIsUsed_ControllerRejects() throws Exception {
        this.mockMvc.perform(post("/api/v1/nibss/bvn-generator"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void whenBvnGeneratorCalled_ContentTypeIsJson() throws Exception {
        this.mockMvc.perform(get("/api/v1/nibss/bvn-generator")
                .contentType("application.json"))
                .andExpect(status().isOk());
    }

    @Test
    void whenBvnGeneratorCalled_ContentIsCustomerTest() throws Exception {
        Customer customer = new Customer("Eesuola", "Popoola", "09089796959");
        this.mockMvc.perform(get("/api/v1/nibss/bvn-generator")
                .contentType("application.json")
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk());
    }
}