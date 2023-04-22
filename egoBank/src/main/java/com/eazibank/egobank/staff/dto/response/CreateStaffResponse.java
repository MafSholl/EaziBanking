package com.eazibank.egobank.staff.dto.response;

import com.eazibank.egobank.staff.models.Department;
import com.eazibank.egobank.atm.models.Location.Location;
import com.eazibank.egobank.staff.models.SubDepartment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStaffResponse {

    private String firstName;
    private String lastName;
    private String staffId;
    private String phoneNumber;
    private String relationshipStatus;
    private Location bankBranch;
    private Department department;
    private SubDepartment subDepartment;
}
