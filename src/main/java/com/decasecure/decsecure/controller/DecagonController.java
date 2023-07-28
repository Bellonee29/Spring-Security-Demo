package com.decasecure.decsecure.controller;

import com.decasecure.decsecure.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/decagon")
public class DecagonController {

//    AuthorizationCode Grant Type
    // Back channel**

    // Register my client
    // Server Redirect with Login Form + Authorization Code
    //  Check code and Credentials

// Password Grant Type
// Client Grant Type

    // Dstv

    private final AppUserService appUserService;

    @GetMapping(path = "/door")
    public ResponseEntity<?> canIcomeIn(){
        log.info("Testing secured endpoint");
        return ResponseEntity.ok("Yes finally you can");
    }

    @GetMapping(path = "/admin")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> canAdminIn(){
        log.info("Testing secured endpoint");
        return ResponseEntity.ok("This is For Admin Yes finally you can");
    }

    @GetMapping(path = "/user")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<?> canUserIn(){
        log.info("Testing secured endpoint User");
        return ResponseEntity.ok("This is For User Yes finally you can");
    }


    // ROLE
    // Privilage
}


