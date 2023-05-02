package com.eazibank.remabank.bank.services;

import com.eazibank.remabank.bank.models.Bank;
import com.eazibank.remabank.customer.dto.request.CreateCustomerRequest;
import com.eazibank.remabank.customer.dto.response.CreateCustomerResponse;
import com.eazibank.remabank.customer.services.CustomerServices;
import com.eazibank.remabank.exception.exceptions.BankDoesNotExistException;
import com.eazibank.remabank.staff.controller.requests.CreateStaffRequest;
import com.eazibank.remabank.staff.models.Staff;
import com.eazibank.remabank.staff.models.StaffDto;
import com.eazibank.remabank.staff.repository.StaffRepository;
import com.eazibank.remabank.staff.services.StaffServices;
import lombok.Builder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServicesImpl implements BankServices {
    @Autowired
    private Bank bank;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerServices customerServices;
//    @Autowired
//    private NibssInterfaceService nibssInterfaceService;
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
//        return nibssInterfaceService.isNibssAvailable();
        return true;
    }


}
