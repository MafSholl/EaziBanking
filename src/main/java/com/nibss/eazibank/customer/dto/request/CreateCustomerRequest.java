package com.nibss.eazibank.customer.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class CreateCustomerRequest {

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String email;
    @NonNull
    private String mothersMaidenName;
    @NonNull
    private String DOB;
    @NonNull
    private String accountType;
}



