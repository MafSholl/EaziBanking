package com.nibss.eazibank.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class RegisterAccountRequest {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String dOB;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String email;
    @NonNull
    private String mothersMaidenName;
    @NonNull
    private String accountType;
}
