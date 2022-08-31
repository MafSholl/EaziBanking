package com.nibss.eazibank.dto.response;

import com.nibss.eazibank.data.models.enums.AccountType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewProfileResponse {

    private String BVN;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private AccountType accountType;
    private LocalDateTime DOB;
}
