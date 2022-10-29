package com.nibss.eazibank.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Document("Nibss Customer")
public class NibssBankUser {
    @Id
    @Indexed(unique = true)
    private String bvn;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private Map<String, List<Account>> bankerInformation = new HashMap<>();

}
