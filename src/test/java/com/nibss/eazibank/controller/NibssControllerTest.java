package com.nibss.eazibank.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nibss.eazibank.data.models.Customer;
import com.nibss.eazibank.dto.request.CreateCustomerRequest;
import com.nibss.eazibank.services.NibssInterface;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
    void whenBvnGeneratorCalled_ParamsExpectedTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/nibss/bvn-generator")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    void whenBvnGeneratorCalled_AndNullRequestBody_ThenReturns400Test() throws Exception {
        Customer customer = new Customer("Eesuola", "Popoola", "09089796959");
        CreateCustomerRequest request = new CreateCustomerRequest();
        this.mockMvc.perform(get("/api/v1/nibss/bvn-generator")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenNibssIsAvailable_BusinessLogicIsCalledTestTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/nibss/is-nibss")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(nibssInterface, times(1)).isNibssAvailable();
    }

    @Test
    void whenBvnGeneratorIsCalled_IsNibssAvailableLogicIsCalled() throws Exception {
        CreateCustomerRequest request = CreateCustomerRequest.builder()
                .firstName("Eesuola")
                .lastName("Popoola")
                .phoneNumber("09089796959")
                .email("epops@example.com")
                .mothersMaidenName("Agilinti")
                .DOB("01-01-2001")
                .accountType("savings")
                .build();
        this.mockMvc.perform(get("/api/v1/nibss/bvn-generator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
        verify(nibssInterface, times(1)).isNibssAvailable();
    }


}