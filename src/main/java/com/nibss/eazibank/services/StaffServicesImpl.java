package com.nibss.eazibank.services;

import com.nibss.eazibank.data.models.Staff;
import com.nibss.eazibank.data.models.StaffDto;
import com.nibss.eazibank.data.repositories.StaffRepository;
import com.nibss.eazibank.dto.request.CreateStaffRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service
@Component
public class StaffServicesImpl implements StaffServices{
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public StaffDto createStaff(CreateStaffRequest createStaffRequest) {
        Staff staff = modelMapper.map(createStaffRequest, Staff.class);
        Staff createdStaff = staffRepository.save(staff);
        return modelMapper.map(createdStaff, StaffDto.class);
    }
}
