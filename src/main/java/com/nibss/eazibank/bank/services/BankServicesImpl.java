package com.nibss.eazibank.bank.services;

import com.nibss.eazibank.customer.services.CustomerServices;
import com.nibss.eazibank.bank.models.Bank;
import com.nibss.eazibank.staff.models.Staff;
import com.nibss.eazibank.staff.models.StaffDto;
import com.nibss.eazibank.staff.repository.StaffRepository;
import com.nibss.eazibank.customer.dto.request.CreateCustomerRequest;
import com.nibss.eazibank.staff.controller.requests.CreateStaffRequest;
import com.nibss.eazibank.customer.dto.response.CreateCustomerResponse;
import com.nibss.eazibank.exception.exceptions.BankDoesNotExistException;
import com.nibss.eazibank.nibss.services.NibssInterfaceService;
import com.nibss.eazibank.staff.services.StaffServices;
import lombok.Builder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServicesImpl implements BankServices {

    private Bank bank;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerServices customerServices;
    @Autowired
    private NibssInterfaceService nibssInterfaceService;
    private StaffServices staffServices;


    @Builder
    public BankServicesImpl(Bank bank) {
        this.bank = bank;
    }

    @Override
    public StaffDto createStaff(CreateStaffRequest createStaffRequest) {
        if (this.bank == null) throw new BankDoesNotExistException("Bank does not exist");
//        Staff newStaff = modelMapper.map(createStaffRequest, Staff.class);
//        return modelMapper.map(savedStaff, CreateStaffResponse.class);
        Staff newStaff = Staff.builder()
                .staffId("1")
                .firstName("Arewa")
                .lastName("Ijaodola")
                .phoneNumber("0908272748")
                .build();
        StaffDto createdStaff = staffServices.createStaff(createStaffRequest);
        Staff savedStaff = staffRepository.save(newStaff);
        return modelMapper.map(newStaff, StaffDto.class);
    }

    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
        return customerServices.createCustomer(createCustomerRequest);
    }

    @Override
    public boolean isNibssInterfaceAvailable() {
        return nibssInterfaceService.isNibssAvailable();
    }


}
