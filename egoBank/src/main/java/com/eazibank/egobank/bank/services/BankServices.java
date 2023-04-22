package com.eazibank.egobank.bank.services;

import com.eazibank.egobank.customer.dto.request.CreateCustomerRequest;
import com.eazibank.egobank.customer.dto.response.CreateCustomerResponse;
import com.eazibank.egobank.staff.controller.requests.CreateStaffRequest;
import com.eazibank.egobank.staff.models.StaffDto;


public interface BankServices {

    StaffDto createStaff(CreateStaffRequest createStaffRequest);

    CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);

    boolean isNibssInterfaceAvailable();
}
