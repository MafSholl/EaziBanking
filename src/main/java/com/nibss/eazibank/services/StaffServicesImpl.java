package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.Customer;
import com.nibss.eazibank.data.models.Staff;
import com.nibss.eazibank.data.models.StaffDto;
import com.nibss.eazibank.data.repositories.CustomerRepository;
import com.nibss.eazibank.data.repositories.StaffRepository;
import com.nibss.eazibank.dto.request.CreateCustomerRequest;
import com.nibss.eazibank.dto.request.CreateStaffRequest;
import com.nibss.eazibank.dto.request.DepositRequest;
import com.nibss.eazibank.dto.response.CreateCustomerResponse;
import com.nibss.eazibank.dto.response.CustomerDepositResponse;
import com.nibss.eazibank.dto.response.StaffDepositDto;
import com.nibss.eazibank.exception.AccountDoesNotExistException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Service
@Component
public class StaffServicesImpl implements StaffServices{
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerServices customerServices;

    public StaffServicesImpl(StaffRepository staffRepository, ModelMapper modelMapper,
                             CustomerServices customerServices) {
        this.staffRepository = staffRepository;
        this.modelMapper = modelMapper;
        this.customerServices = customerServices;
    }

    @Override
    public StaffDto createStaff(CreateStaffRequest createStaffRequest) {
        Staff staff = modelMapper.map(createStaffRequest, Staff.class);
        Staff createdStaff = staffRepository.save(staff);
        return modelMapper.map(createdStaff, StaffDto.class);
    }

    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
        return customerServices.createCustomer(createCustomerRequest);
    }

    @Override
    public StaffDepositDto customersAccountDeposit(DepositRequest depositRequest, String staffId) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByCustomerAccount(depositRequest.getAccountNumber());
        if(optionalCustomer.isEmpty()) throw new AccountDoesNotExistException("Account does not exist");
        CustomerDepositResponse depositResponse = customerServices.deposit(depositRequest);
        StaffDepositDto staffDepositDto = modelMapper.map(depositResponse, StaffDepositDto.class);
        staffDepositDto.setStaffId(staffId);
        return staffDepositDto;
    }
}
