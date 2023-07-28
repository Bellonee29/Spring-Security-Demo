package com.decasecure.decsecure.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    @NotBlank(message = "password is required")
    @Size(max = 15, min = 6, message = "Password should be Greater 6 but less than 15")
    private String password;

    @NotBlank(message = "Confirm Password is required")
    @Size(max = 15, min = 6, message = "Confirm Password should be Greater 6 but less than 15")
    private String confirmPassword;

    @NotBlank(message = "Email is required")
    @Email(message = "provide a valid email")
    private String email;

    @NotBlank(message = "full name is required")
    private String fullName;

    private String userName;
}
