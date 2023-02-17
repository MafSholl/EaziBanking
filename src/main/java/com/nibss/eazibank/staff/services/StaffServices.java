package com.nibss.eazibank.staff.services;

import com.nibss.eazibank.staff.models.StaffDto;
import com.nibss.eazibank.customer.dto.request.CreateCustomerRequest;
import com.nibss.eazibank.staff.controller.requests.CreateStaffRequest;
import com.nibss.eazibank.staff.controller.requests.DepositRequest;
import com.nibss.eazibank.customer.dto.response.CreateCustomerResponse;
import com.nibss.eazibank.staff.dto.StaffDepositDto;

public interface StaffServices {

    StaffDto createStaff(CreateStaffRequest createStaffRequest);

    CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);

    StaffDepositDto customersAccountDeposit(DepositRequest depositRequest, String staffId);
}
