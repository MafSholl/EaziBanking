package com.eazibank.egobank.staff.services;

import com.eazibank.egobank.customer.dto.request.CreateCustomerRequest;
import com.eazibank.egobank.customer.dto.response.CreateCustomerResponse;
import com.eazibank.egobank.staff.controller.requests.CreateStaffRequest;
import com.eazibank.egobank.staff.models.StaffDto;
import com.eazibank.egobank.staff.controller.requests.DepositRequest;
import com.eazibank.egobank.staff.dto.StaffDepositDto;

public interface StaffServices {

    StaffDto createStaff(CreateStaffRequest createStaffRequest);

    CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);

    StaffDepositDto customersAccountDeposit(DepositRequest depositRequest, String staffId);
}
