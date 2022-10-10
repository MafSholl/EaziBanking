package com.nibss.eazibank.data.models;

import com.nibss.eazibank.data.models.enums.Department;
import com.nibss.eazibank.data.models.enums.Location.Location;
import com.nibss.eazibank.data.models.enums.OfficePosition;
import com.nibss.eazibank.data.models.enums.SalaryLevel;
import com.nibss.eazibank.data.models.enums.SubDepartment;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigInteger;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {
    @Id
    @Indexed(unique = true)
    private String staffId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String DOB;
    private String relationshipStatus;
    private BigInteger salary;
    private Department department;
    private SubDepartment subDepartment;
    private SalaryLevel salaryLevel;
    private OfficePosition officePosition;
    private Bank bank;
    private Location bankBranch;
}
