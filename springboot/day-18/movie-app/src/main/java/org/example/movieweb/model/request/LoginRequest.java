package org.example.movieweb.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must be valid")
    String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 3, message = "Password must be at least 3 characters")
    String password;
}
