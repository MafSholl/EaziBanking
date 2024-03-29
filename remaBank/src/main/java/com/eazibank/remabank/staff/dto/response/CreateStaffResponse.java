package com.eazibank.remabank.staff.dto.response;

import com.eazibank.remabank.staff.models.enums.Department;
import com.eazibank.remabank.atm.models.Location.Location;
import com.eazibank.remabank.staff.models.enums.SubDepartment;
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
