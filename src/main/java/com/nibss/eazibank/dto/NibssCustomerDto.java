package com.nibss.eazibank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NibssCustomerDto {

    private String bvn;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String bankName;
    private String accountType;
}
