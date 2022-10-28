package com.nibss.eazibank.dto;

import com.nibss.eazibank.dto.request.RegisterAccountRequest;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBvnDto {

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

    @Builder
    public CreateBvnDto(RegisterAccountRequest request) {
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.phoneNumber = request.getPhoneNumber();
        this.email = request.getEmail();
        this.mothersMaidenName = request.getMothersMaidenName();
        this.DOB = request.getDOB();
        this.accountType = request.getAccountType();
    }
}
