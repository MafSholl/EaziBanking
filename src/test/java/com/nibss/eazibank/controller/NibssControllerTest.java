package com.nibss.eazibank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nibss.eazibank.controller.response.ApiResponse;
import com.nibss.eazibank.data.models.Account;
import com.nibss.eazibank.data.models.Customer;
import com.nibss.eazibank.data.models.enums.AccountType;
import com.nibss.eazibank.dto.CreateBvnDto;
import com.nibss.eazibank.dto.NibssBankUserDto;
import com.nibss.eazibank.dto.NibssInterbankDto;
import com.nibss.eazibank.dto.request.CreateCustomerRequest;
import com.nibss.eazibank.services.NibssInterfaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NibssController.class)
class NibssControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NibssInterfaceService nibssInterfaceService;
    @Autowired
    private ObjectMapper objectMapper;
    private CreateBvnDto createBvnRequest;
    private CreateBvnDto createBvnRequest1;
    @BeforeEach
    void setUp() {
        CreateBvnDto createBvnRequest = CreateBvnDto.builder()
                .firstName("Eesuola")
                .lastName("Popoola")
                .phoneNumber("09089796959")
                .email("epops@example.com")
                .mothersMaidenName("Agilinti")
                .accountType("savings")
                .build();
        this.createBvnRequest = createBvnRequest;

        CreateBvnDto createBvnRequest1 = CreateBvnDto.builder()
                .firstName("Eesuola")
                .lastName("Popoola")
                .phoneNumber("09089796959")
                .email("epops@example.com")
                .mothersMaidenName("Agilinti")
                .accountType("savings")
                .build();
        this.createBvnRequest1 = createBvnRequest1;


    }

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
    void whenBvnGeneratorCalled_ResponseStatusIsOk() throws Exception {
        this.mockMvc.perform(post("/api/v1/nibss/bvn-generator"))
                .andExpect(status().isOk());
    }

    @Test
    void whenWrongHttpMethodIsUsed_ControllerRejects() throws Exception {
        this.mockMvc.perform(post("/api/v1/nibss/bvn-generator"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void whenBvnGeneratorCalled_ParamsExpectedTest() throws Exception {
        this.mockMvc.perform(post("/api/v1/nibss/bvn-generator")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    void whenBvnGeneratorCalled_AndNullRequestBody_ThenReturns400Test() throws Exception {
        CreateCustomerRequest request = new CreateCustomerRequest();
        this.mockMvc.perform(post("/api/v1/nibss/bvn-generator")
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
        verify(nibssInterfaceService, times(1)).isNibssAvailable();
    }

    @Test
    void whenIsNibssAvailableCalled_IfNotAvailable_ResponseIsServerError() throws Exception {
        boolean falseBoolean = false;
        when(nibssInterfaceService.isNibssAvailable()).thenReturn(falseBoolean);

        this.mockMvc.perform(get("/api/v1/nibss/is-nibss")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void whenBvnGeneratorIsCalled_IsNibssAvailableLogicIsCalled() throws Exception {
        this.mockMvc.perform(post("/api/v1/nibss/bvn-generator")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.createBvnRequest)))
                .andExpect(status().isOk());
        verify(nibssInterfaceService, times(1)).isNibssAvailable();
    }
    @Test
    void whenBvnGeneratorIsCalled_NibssLogicReturnsTrue() throws Exception{
        boolean trueBoolean = true;
        ApiResponse expectedResponse = ApiResponse.builder()
                .status("success")
                .message("BVN created successfully")
                .data(trueBoolean)
                .statusCode(HttpStatus.OK.value())
                .build();
        when(nibssInterfaceService.isNibssAvailable()).thenReturn(trueBoolean);

        MvcResult result = this.mockMvc.perform(post("/api/v1/nibss/bvn-generator")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedResponse)))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponse = result.getResponse().getContentAsString();
        assertThat(actualResponse).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expectedResponse));
    }

    @Test
    void whenBvnGeneratorIsCalled_ValidDtoIsPassed_Returns200() throws Exception{
        this.mockMvc.perform(post("/api/v1/nibss/bvn-generator")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.createBvnRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void whenBvnGeneratorCalled_NibssReturnANewCustomer() throws Exception{

        CreateBvnDto createBvnRequest = CreateBvnDto.builder()
                .firstName("Eesuola")
                .lastName("Popoola")
                .phoneNumber("09089796959")
                .email("epops@example.com")
                .mothersMaidenName("Agilinti")
                .accountType("savings")
                .build();
        this.createBvnRequest = createBvnRequest;

        NibssBankUserDto returnedNibssBankUserDto = NibssBankUserDto.builder()
                .bvn(String.valueOf(1000000000))
                .firstName("Eesuola")
                .lastName("Popoola")
                .build();
        ApiResponse expectedResponse = ApiResponse.builder()
                .status("success")
                .message("BVN created successfully")
                .data(returnedNibssBankUserDto)
                .statusCode(HttpStatus.OK.value())
                .build();
        when(nibssInterfaceService.bvnGenerator(createBvnRequest)).thenReturn(returnedNibssBankUserDto);

        MvcResult result = this.mockMvc.perform(post("/api/v1/nibss/bvn-generator")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createBvnRequest)))
                .andExpect(status().isOk())
                .andReturn();
        String actualResponse = result.getResponse().getContentAsString();
        assertThat(actualResponse).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expectedResponse));
    }

    @Test
    public void whenInterbankTransaction_NibssLinksBothBanksTogether() throws Exception{
        NibssBankUserDto returnedNibssBankUserDto = NibssBankUserDto.builder()
                .bvn(String.valueOf(1000000001))
                .firstName("Joshua")
                .lastName("Kekere-ekun")
                .build();

        Account account = new Account();
        account.setFirstName("Joshua");
        account.setLastName("Kekere-ekun");
        account.setPhoneNumber("07012345678");
        account.setAccountType(AccountType.SAVINGS);
        account.setAccountNumber("0123456789");
        account.setBvn("2000100010");
        Optional<Account> optionalAccount = Optional.of(account);
        Map<String, Account> customerAccounts = new HashMap<>();
        customerAccounts.put(account.getAccountNumber(), account);

        CreateCustomerRequest createCustomerRequest = new  CreateCustomerRequest("Ayobaye", "Ogundele",
                "07048847840", "", "", "2000-01-20 00:00", "SAVINGS");
        Customer customer = new Customer();
        customer.setFirstName("Joshua");
        customer.setLastName("Kekere-ekun");
        customer.setPhoneNumber("07012345678");
        customer.setBvn(String.valueOf(1000000001));
        customer.setEmail("");
        customer.setCustomerAccounts(customerAccounts);

        NibssInterbankDto nibssDepositRequest = NibssInterbankDto.builder()
                .accountNumber(account.getAccountNumber())
                .amount(new BigInteger("3000"))
                .firstName("Joshua")
                .lastName("Kekere-ekun")
                .bankId("02")
                .bank("KaboBank")
                .build();


        when(nibssInterfaceService.nibssInterbankDeposit(nibssDepositRequest)).thenReturn(returnedNibssBankUserDto);

        ApiResponse expectedResponse = ApiResponse.builder()
                .status("success")
                .message("Transfer successful")
                .data(returnedNibssBankUserDto)
                .statusCode(HttpStatus.OK.value())
                .build();

        MvcResult result = this.mockMvc.perform(put("/api/v1/nibss/nibss-transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nibssDepositRequest)))
                .andExpect(status().isOk())
                .andReturn();
        String actualResponseBody = result.getResponse().getContentAsString();

        verify(nibssInterfaceService).nibssInterbankDeposit(nibssDepositRequest);
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expectedResponse));
    }
}