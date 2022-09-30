package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.*;
import com.nibss.eazibank.data.repositories.StaffRepository;
import com.nibss.eazibank.dto.request.CreateCustomerRequest;
import com.nibss.eazibank.dto.request.CreateStaffRequest;
import com.nibss.eazibank.dto.response.CreateCustomerResponse;
import com.nibss.eazibank.dto.response.CreateStaffResponse;
import com.nibss.eazibank.exception.BankDoesNotExistException;
import lombok.Builder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServicesImpl implements BankServices{

    private Bank bank;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerServices customerServices;
    @Autowired
    private NibssInterface nibssInterface;
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
        return nibssInterface.isNibssAvailable();
    }


}
