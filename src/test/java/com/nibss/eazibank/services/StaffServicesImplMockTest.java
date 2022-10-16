package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.Account;
import com.nibss.eazibank.data.models.Customer;
import com.nibss.eazibank.data.models.Staff;
import com.nibss.eazibank.data.models.StaffDto;
import com.nibss.eazibank.data.models.enums.AccountType;
import com.nibss.eazibank.data.repositories.CustomerRepository;
import com.nibss.eazibank.data.repositories.StaffRepository;
import com.nibss.eazibank.dto.request.CreateCustomerRequest;
import com.nibss.eazibank.dto.request.CreateStaffRequest;
import com.nibss.eazibank.dto.request.DepositRequest;
import com.nibss.eazibank.dto.request.RegisterAccountRequest;
import com.nibss.eazibank.dto.response.CreateCustomerResponse;
import com.nibss.eazibank.dto.response.CustomerDepositResponse;
import com.nibss.eazibank.dto.response.RegisterAccountResponse;
import com.nibss.eazibank.dto.response.StaffDepositDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StaffServicesImplMockTest {
//    @InjectMocks
    private StaffServices staffServices;
    @Mock
    private StaffRepository staffRepository;
    @Mock(lenient = true)
    private ModelMapper modelMapper;
    @Captor
    private ArgumentCaptor<Staff> staffArgumentCaptor;
    @Captor
    private ArgumentCaptor<Customer> customerArgumentCaptor;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServices customerServices = new CustomerServicesImpl();
    @Mock
    private CustomerServices customerServices1;
    @Mock
    private AccountServices accountServices;

    @BeforeEach
    void setup() {
        staffServices = new StaffServicesImpl(staffRepository, modelMapper, customerServices);
    }

    @Test
    public void staffServicesExistTest() {
        assertThat(staffServices).isNotNull();
    }

    @Test
    public void staffCanBeCreatedTest() {
        CreateStaffRequest createStaffRequest = new CreateStaffRequest("Arewa", "Ijaodola",
                "0908272748", "2001-01-01", "single");
        Staff createdStaff = Staff.builder()
                .staffId("1")
                .firstName(createStaffRequest.getFirstName())
                .lastName(createStaffRequest.getLastName())
                .phoneNumber(createStaffRequest.getPhoneNumber())
                .build();
        StaffDto staffDtoReturned = StaffDto.builder()
                .staffId("1")
                .firstName(createStaffRequest.getFirstName())
                .lastName(createStaffRequest.getLastName())
                .phoneNumber(createStaffRequest.getPhoneNumber())
                .build();

        when(staffRepository.save(any(Staff.class))).thenReturn(createdStaff);
        when(modelMapper.map(createStaffRequest, Staff.class)).thenReturn(createdStaff);
        when(modelMapper.map(createdStaff, StaffDto.class)).thenReturn(staffDtoReturned);
//        when(staffServices.createStaff(any(CreateStaffRequest.class))).thenReturn(staffDtoReturned)
        StaffDto newStaff = staffServices.createStaff(createStaffRequest);
        verify(staffRepository, times(1)).save(staffArgumentCaptor.capture());

        Staff capturedStaff = staffArgumentCaptor.getValue();
        assertThat(capturedStaff.getStaffId()).isEqualTo("1");
        assertThat(newStaff.getStaffId()).isEqualTo("1");
        assertThat(newStaff.getFirstName()).isEqualTo(capturedStaff.getFirstName());
        assertThat(newStaff.getLastName()).isEqualTo(capturedStaff.getLastName());
        assertThat(newStaff.getPhoneNumber()).isEqualTo(capturedStaff.getPhoneNumber());
    }

    @Test
    public void staffCanCreateCustomerTest() {
        CreateCustomerRequest createCustomerRequest = new  CreateCustomerRequest("Ayobaye", "Ogundele",
                "07048847840", "", "", "2000-01-20 00:00", "SAVINGS");
        RegisterAccountRequest registerAccountRequest = new RegisterAccountRequest("Adeola", "Ololade",
                "01-01-1991","08101234568", "Ayoola", "Afolabi", "savings");
        Account account = new Account();
        account.setFirstName(createCustomerRequest.getFirstName());
        account.setLastName(createCustomerRequest.getLastName());
        account.setPhoneNumber(createCustomerRequest.getPhoneNumber());
        account.setAccountType(AccountType.SAVINGS);
        account.setAccountNumber("0123456789");
        account.setBankVerificationNumber("2000100010");
        Optional<Account> optionalAccount = Optional.of(account);

        RegisterAccountResponse registerAccountResponse = RegisterAccountResponse.builder()
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .accountNumber(account.getAccountNumber())
                .bankVerificationNumber(account.getBankVerificationNumber())
                .balance(BigInteger.ZERO)
                .success("true")
                .build();

        Map<String, Account> customerAccounts = new HashMap<>();
        customerAccounts.put(account.getAccountNumber(), account);

        Customer customer = new Customer();
        customer.setFirstName(createCustomerRequest.getFirstName());
        customer.setLastName(createCustomerRequest.getLastName());
        customer.setPhoneNumber(createCustomerRequest.getPhoneNumber());
        customer.setBVN(account.getBankVerificationNumber());
        customer.setEmail("");
        customer.setCustomerAccounts(customerAccounts);

        Optional<Customer> optionalCustomer = Optional.empty();

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(createCustomerRequest.getFirstName());
        savedCustomer.setLastName(createCustomerRequest.getLastName());
        savedCustomer.setPhoneNumber(createCustomerRequest.getPhoneNumber());
        savedCustomer.setBVN(account.getBankVerificationNumber());
        savedCustomer.setEmail("");
        savedCustomer.setCustomerAccounts(customerAccounts);

        CreateCustomerResponse createCustomerResponse = CreateCustomerResponse.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .accountNumber(account.getAccountNumber())
                .accountType(String.valueOf(account.getAccountType()))
                .balance(BigInteger.ZERO)
                .BVN(account.getBankVerificationNumber())
                .email(customer.getEmail())
                .build();

        when(modelMapper.map(createCustomerRequest, Customer.class)).thenReturn(customer);
        when(modelMapper.map(customer, RegisterAccountRequest.class)).thenReturn(registerAccountRequest);
        when(modelMapper.map(savedCustomer, CreateCustomerResponse.class)).thenReturn(createCustomerResponse);
        when(accountServices.createAccount(any(RegisterAccountRequest.class))).thenReturn(registerAccountResponse);
        when(accountServices.findAccount(any(String.class))).thenReturn(optionalAccount);
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
//        when(customerRepository.findByMothersMaidenName(any(String.class))).thenReturn(optionalCustomer);
//        when(customerServices.createCustomer(createCustomerRequest)).thenReturn(createCustomerResponse);

        CreateCustomerResponse newCustomer = staffServices.createCustomer(createCustomerRequest);
        verify(customerRepository).save(customer);
        verify(customerRepository, times(1)).save(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();
        assertThat(capturedCustomer.getBVN()).isEqualTo(newCustomer.getBVN());
    }

    @Test
    void staffCanDepositIntoCustomerAccountTest() {
        CreateStaffRequest createStaffRequest = new CreateStaffRequest("Arewa", "Ijaodola",
                "0908272748", "2001-01-01", "single");
        Staff createdStaff = Staff.builder()
                .staffId("1")
                .firstName(createStaffRequest.getFirstName())
                .lastName(createStaffRequest.getLastName())
                .phoneNumber(createStaffRequest.getPhoneNumber())
                .build();
        StaffDto staffDtoReturned = StaffDto.builder()
                .staffId("1")
                .firstName(createStaffRequest.getFirstName())
                .lastName(createStaffRequest.getLastName())
                .phoneNumber(createStaffRequest.getPhoneNumber())
                .build();

        when(modelMapper.map(createStaffRequest, Staff.class)).thenReturn(createdStaff);
        when(staffRepository.save(any(Staff.class))).thenReturn(createdStaff);
        when(modelMapper.map(createdStaff, StaffDto.class)).thenReturn(staffDtoReturned);
        StaffDto newStaff = staffServices.createStaff(createStaffRequest);
        verify(staffRepository, times(1)).save(createdStaff);

        Account account = new Account();
        account.setFirstName("Ayobaye");
        account.setLastName("Ogundele");
        account.setPhoneNumber("07048847840");
        account.setAccountType(AccountType.SAVINGS);
        account.setAccountNumber("0123456789");
        account.setBankVerificationNumber("2000100010");
        Optional<Account> optionalAccount = Optional.of(account);

        DepositRequest depositRequest = new DepositRequest("0123456789", BigInteger.valueOf(100000));
        CustomerDepositResponse returnedDepositResponse = new CustomerDepositResponse("Ayobaye", "Ogundele",
                "07048847840", BigInteger.valueOf(100000), true);
        StaffDepositDto staffDepositResponse = StaffDepositDto.builder()
                .firstName("Ayobaye")
                .lastName("Ogundele")
                .accountNumber("07048847840")
                .amount(depositRequest.getAmount())
                .success(true)
                .staffId("1")
                .build();
        when(staffRepository.findById("1")).thenReturn(Optional.of(createdStaff));
        when(customerServices1.deposit(any(DepositRequest.class))).thenReturn(returnedDepositResponse);
        when(modelMapper.map(returnedDepositResponse, StaffDepositDto.class)).thenReturn(staffDepositResponse);

        StaffDepositDto depositResponse = staffServices.customersAccountDeposit(depositRequest, "1");
        assertThat(depositResponse.getAmount()).isEqualTo(staffDepositResponse.getAmount());
    }
}