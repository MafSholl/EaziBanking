package com.nibss.eazibank.bank.services;

import com.nibss.eazibank.staff.models.StaffDto;
import com.nibss.eazibank.customer.dto.request.CreateCustomerRequest;
import com.nibss.eazibank.staff.controller.requests.CreateStaffRequest;
import com.nibss.eazibank.customer.dto.response.CreateCustomerResponse;


public interface BankServices {

    StaffDto createStaff(CreateStaffRequest createStaffRequest);

    CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);

    boolean isNibssInterfaceAvailable();
}
