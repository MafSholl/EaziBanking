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
import com.nibss.eazibank.dto.request.CustomerDepositRequest;
import com.nibss.eazibank.dto.request.RegisterAccountRequest;
import com.nibss.eazibank.dto.response.CreateCustomerResponse;
import com.nibss.eazibank.dto.response.CustomerDepositResponse;
import com.nibss.eazibank.dto.response.RegisterAccountResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigInteger;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StaffServicesImplMockTest {
    @InjectMocks
    private StaffServices staffServices = new StaffServicesImpl();
    @Mock
    private StaffRepository staffRepository;
    @Mock
    private ModelMapper modelMapper;
    @Captor
    private ArgumentCaptor<Staff> staffArgumentCaptor;
    @Captor
    private ArgumentCaptor<Customer> customerArgumentCaptor;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerServices customerServices;
    @Mock
    private AccountServices accountServices;

//    @BeforeEach
//    void setup() {
//        staffServices = new StaffServicesImpl();
//    }

    @Test
    public void staffServicesExistTest() {
        StaffServices staffServices = new StaffServicesImpl();
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
    public void staffCanDepositIntoCustomersAccountTest() {
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

        StaffDto newStaff = staffServices.createStaff(createStaffRequest);
        verify(staffRepository).save(createdStaff);
        verify(staffRepository, times(1)).save(staffArgumentCaptor.capture());

        Staff capturedStaff = staffArgumentCaptor.getValue();
        assertThat(capturedStaff.getStaffId()).isEqualTo("1");
        assertThat(newStaff.getStaffId()).isEqualTo("1");
        assertThat(newStaff.getFirstName()).isEqualTo(capturedStaff.getFirstName());
        assertThat(newStaff.getLastName()).isEqualTo(capturedStaff.getLastName());
        assertThat(newStaff.getPhoneNumber()).isEqualTo(capturedStaff.getPhoneNumber());


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

        RegisterAccountResponse registerAccountResponse = RegisterAccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .success("true")
                .build();

        Customer customer = new Customer();
        customer.setFirstName(createCustomerRequest.getFirstName());
        customer.setLastName(createCustomerRequest.getLastName());
        customer.setPhoneNumber(createCustomerRequest.getPhoneNumber());
        customer.setBVN(account.getBankVerificationNumber());
        customer.setEmail("");

        Customer savedCustomer = new Customer();
        customer.setFirstName(createCustomerRequest.getFirstName());
        customer.setLastName(createCustomerRequest.getLastName());
        customer.setPhoneNumber(createCustomerRequest.getPhoneNumber());
        customer.setBVN(account.getBankVerificationNumber());
        customer.setEmail("");

        CreateCustomerResponse createCustomerResponse = CreateCustomerResponse.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .accountNumber(account.getAccountNumber())
                .accountType(String.valueOf(account.getAccountType()))
                .balance(BigInteger.ZERO)
                .BVN(account.getBankVerificationNumber())
                .email(customer.getEmail())
                .build();
        CustomerDepositRequest depositRequest = new CustomerDepositRequest(account.getAccountNumber(), BigInteger.valueOf(100000000));
        Optional<Account> optionalAccount = Optional.of(account);

        when(modelMapper.map(createCustomerRequest, Customer.class)).thenReturn(customer);
        when(modelMapper.map(customer, RegisterAccountRequest.class)).thenReturn(registerAccountRequest);
        when(modelMapper.map(customer, CreateCustomerResponse.class)).thenReturn(createCustomerResponse);
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
        when(accountServices.createAccount(any(RegisterAccountRequest.class))).thenReturn(registerAccountResponse);
        when(accountServices.findAccount(any(String.class))).thenReturn(optionalAccount);

        CreateCustomerResponse createdCustomer = customerServices.createCustomer(createCustomerRequest);
        verify(customerRepository).save(customer);
        verify(customerRepository, times(1)).save(customerArgumentCaptor.capture());
        Customer newCustomer = customerArgumentCaptor.getValue();
        assertThat(createdCustomer.getBVN()).isEqualTo(newCustomer.getBVN());

//        CustomerDepositResponse depositResponse = customerServices.deposit(depositRequest);


    }
}