package com.eazibank.nibss.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NibssBankUserDto {

    private String bvn;
    private String firstName;
    private String lastName;
}
