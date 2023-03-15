package com.eazibank.remabank.staff.models;

import com.eazibank.remabank.bank.models.Bank;
import com.eazibank.remabank.data.models.enums.Department;
import com.eazibank.remabank.data.models.enums.Location.Location;
import com.eazibank.remabank.data.models.enums.OfficePosition;
import com.eazibank.remabank.data.models.enums.SalaryLevel;
import com.eazibank.remabank.data.models.enums.SubDepartment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StaffDto {

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
