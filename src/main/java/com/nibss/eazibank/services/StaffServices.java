package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.StaffDto;
import com.nibss.eazibank.dto.request.CreateCustomerRequest;
import com.nibss.eazibank.dto.request.CreateStaffRequest;
import com.nibss.eazibank.dto.request.DepositRequest;
import com.nibss.eazibank.dto.response.CreateCustomerResponse;
import com.nibss.eazibank.dto.response.StaffDepositDto;

public interface StaffServices {

    StaffDto createStaff(CreateStaffRequest createStaffRequest);

    CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);

    StaffDepositDto customersAccountDeposit(DepositRequest depositRequest, String staffId);
}
