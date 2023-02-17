package com.nibss.eazibank.account.dto.response;

import com.nibss.eazibank.account.models.Account;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBvnDto {

    private Long bankId;
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
    private String accountType;
    private String accountNumber;
    private String bvn;

    @Builder
    public CreateBvnDto(Account account) {
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.phoneNumber = account.getPhoneNumber();
        this.email = account.getEmail();
        this.mothersMaidenName = account.getMothersMaidenName();
        this.accountType = String.valueOf(account.getAccountType());
        this.accountNumber = account.getAccountNumber();
    }
}
