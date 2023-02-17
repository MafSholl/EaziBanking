package com.nibss.eazibank.customer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePasswordResponse {

    private String firstName;
    private String lastName;
    private boolean success;
}
