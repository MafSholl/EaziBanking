package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.StaffDto;
import com.nibss.eazibank.dto.request.CreateStaffRequest;

public interface StaffServices {

    StaffDto createStaff(CreateStaffRequest createStaffRequest);
}
