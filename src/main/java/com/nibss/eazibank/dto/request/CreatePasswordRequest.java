package com.nibss.eazibank.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePasswordRequest {

    @NonNull
    private String password;
    @NonNull
    private String confirmPassword;
    @NonNull
    private String email;
}
