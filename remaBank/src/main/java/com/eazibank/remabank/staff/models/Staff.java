package com.eazibank.remabank.staff.models;

import com.eazibank.remabank.atm.models.Location.Location;
import com.eazibank.remabank.staff.models.enums.Department;
import com.eazibank.remabank.staff.models.enums.OfficePosition;
import com.eazibank.remabank.staff.models.enums.SalaryLevel;
import com.eazibank.remabank.staff.models.enums.SubDepartment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@Document("Staff")
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
//    private Bank bank;
    private Location bankBranch;
}
