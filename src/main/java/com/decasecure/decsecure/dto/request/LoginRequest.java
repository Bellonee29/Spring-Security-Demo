package com.decasecure.decsecure.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {


//    NotBlank --> Trims fields the field and checks the length
//    NotEmpty --> Check if it contains something & checks null
//    NotNull

    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "password is required")
    private String password;
}
