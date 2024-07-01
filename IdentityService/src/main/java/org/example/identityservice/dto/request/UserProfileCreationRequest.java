package org.example.identityservice.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileCreationRequest {
    private String userId;
    private String firstName, lastName;

    private LocalDate dob;
    private String city;
}
