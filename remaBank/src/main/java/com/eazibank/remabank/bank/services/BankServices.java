package com.eazibank.remabank.bank.services;

import com.eazibank.remabank.customer.dto.request.CreateCustomerRequest;
import com.eazibank.remabank.customer.dto.response.CreateCustomerResponse;
import com.eazibank.remabank.staff.controller.requests.CreateStaffRequest;
import com.eazibank.remabank.staff.models.StaffDto;


public interface BankServices {

    StaffDto createStaff(CreateStaffRequest createStaffRequest);

    CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);

    boolean isNibssInterfaceAvailable();
}
