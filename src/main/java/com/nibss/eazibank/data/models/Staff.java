package com.nibss.eazibank.data.models;

import com.nibss.eazibank.data.models.enums.Department;
import com.nibss.eazibank.data.models.enums.OfficePosition;
import com.nibss.eazibank.data.models.enums.SalaryLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
@Data
@NoArgsConstructor
//@RequiredArgsConstructor
//@AllArgsConstructor
public class Staff {
    private long id;
    private String firstName;
    private String lastName;
    private BigInteger salary;
    private Department department;
    private SalaryLevel salaryLevel;
    private OfficePosition officePosition;
}
