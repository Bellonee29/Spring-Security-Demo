package com.decasecure.decsecure.service;

import com.decasecure.decsecure.dto.request.LoginRequest;
import com.decasecure.decsecure.dto.request.RegistrationRequest;
import com.decasecure.decsecure.dto.response.ApiResponse;
import com.decasecure.decsecure.dto.response.LoginResponse;

public interface AppUserService {

    // ttl --> time to live

    // Uniform Interface == ApiResponse/ AppResponse / ServiceResponse

    ApiResponse<LoginResponse> loginUser(LoginRequest request);

    ApiResponse<String> registerUser(RegistrationRequest request);

    ApiResponse<String> paySalary();

}
