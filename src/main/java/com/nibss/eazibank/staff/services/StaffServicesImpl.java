package com.nibss.eazibank.staff.services;

import com.nibss.eazibank.customer.services.CustomerServices;
import com.nibss.eazibank.staff.models.Staff;
import com.nibss.eazibank.staff.models.StaffDto;
import com.nibss.eazibank.customer.repository.CustomerRepository;
import com.nibss.eazibank.staff.repository.StaffRepository;
import com.nibss.eazibank.customer.dto.request.CreateCustomerRequest;
import com.nibss.eazibank.staff.controller.requests.CreateStaffRequest;
import com.nibss.eazibank.staff.controller.requests.DepositRequest;
import com.nibss.eazibank.customer.dto.response.CreateCustomerResponse;
import com.nibss.eazibank.customer.dto.response.CustomerDepositResponse;
import com.nibss.eazibank.staff.dto.StaffDepositDto;
import com.nibss.eazibank.exception.exceptions.EaziBankExceptions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Service
@Component
public class StaffServicesImpl implements StaffServices {
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
        Optional<Staff> staff = staffRepository.findById(staffId);
        if (staff.isEmpty()) throw new EaziBankExceptions("Staff does ot exist", HttpStatus.NO_CONTENT.value());
        CustomerDepositResponse depositResponse = customerServices.deposit(depositRequest);
        StaffDepositDto staffDepositDto = modelMapper.map(depositResponse, StaffDepositDto.class);
        staffDepositDto.setStaffId(staffId);
        return staffDepositDto;
    }
}
