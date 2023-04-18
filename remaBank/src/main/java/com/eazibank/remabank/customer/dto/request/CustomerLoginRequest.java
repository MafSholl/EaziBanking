package com.eazibank.remabank.customer.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerLoginRequest {

    private String email;
    private String phoneNumber;
    @NonNull
    private String password;
}
