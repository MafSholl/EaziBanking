package com.nibss.eazibank.data.models;

import com.nibss.eazibank.data.models.enums.Department;
import com.nibss.eazibank.data.models.enums.OfficePosition;
import com.nibss.eazibank.data.models.enums.SalaryLevel;
import com.nibss.eazibank.data.models.enums.SubDepartment;
import lombok.*;

import java.math.BigInteger;
@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {
    private long id;
    private String firstName;
    private String lastName;
    private BigInteger salary;
    private Department department;
    private SubDepartment subDepartment;
    private SalaryLevel salaryLevel;
    private OfficePosition officePosition;
}
