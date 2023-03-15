package com.eazibank.remabank.staff.services;

import com.eazibank.remabank.customer.dto.request.CreateCustomerRequest;
import com.eazibank.remabank.customer.dto.response.CreateCustomerResponse;
import com.eazibank.remabank.staff.controller.requests.CreateStaffRequest;
import com.eazibank.remabank.staff.models.StaffDto;
import com.eazibank.remabank.staff.controller.requests.DepositRequest;
import com.eazibank.remabank.staff.dto.StaffDepositDto;

public interface StaffServices {

    StaffDto createStaff(CreateStaffRequest createStaffRequest);

    CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);

    StaffDepositDto customersAccountDeposit(DepositRequest depositRequest, String staffId);
}
