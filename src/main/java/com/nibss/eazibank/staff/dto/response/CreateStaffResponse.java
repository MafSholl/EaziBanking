package com.nibss.eazibank.staff.dto.response;

import com.nibss.eazibank.data.models.enums.Department;
import com.nibss.eazibank.data.models.enums.Location.Location;
import com.nibss.eazibank.data.models.enums.SubDepartment;
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
