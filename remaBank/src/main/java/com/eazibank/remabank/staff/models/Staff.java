package com.eazibank.remabank.staff.models;

import com.eazibank.remabank.bank.models.Bank;
import com.eazibank.remabank.atm.models.Location.Location;
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
