package com.decasecure.decsecure.controller;

import com.decasecure.decsecure.dto.request.LoginRequest;
import com.decasecure.decsecure.dto.request.RegistrationRequest;
import com.decasecure.decsecure.exceptions.CustomException;
import com.decasecure.decsecure.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AppUserService appUserService;


    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginRequest request) {
        log.info("request to login user");

        var response = appUserService.loginUser(request);

        if (response.getStatus()) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body(response);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationRequest request) {
        // Validate --- util
        log.info("register request");
        isRequestValid(request);
        // send to service
        var response = appUserService.registerUser(request);

        if (response.getStatus()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    public static boolean isRequestValid(RegistrationRequest request) {
        log.info("validate register request");
        String password = request.getPassword();
        String confirmPassword = request.getConfirmPassword();

        // Check if contains capital and small letters

        // Check if match

        if (Objects.equals(password, confirmPassword)) {
            return true;
        } else {
            throw new CustomException("Password and Confirm Password must match", HttpStatus.BAD_REQUEST);
        }
    }
}
