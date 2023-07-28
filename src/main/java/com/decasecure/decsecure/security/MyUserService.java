package com.decasecure.decsecure.security;

import com.decasecure.decsecure.enitity.AppUser;
import com.decasecure.decsecure.exceptions.CustomException;
import com.decasecure.decsecure.repository.AppUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MyUserService implements UserDetailsService {

    private final AppUserRepo appUserRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepo.findByEmail(username).orElse(null);

        if (appUser == null) {
            throw new CustomException("Invalid Credentials", HttpStatus.UNAUTHORIZED);
        }
        List<String> fakeRoles = List.of("User","Admin", "Officer");
        return new User(username, appUser.getPassword(), getAuthorities(fakeRoles));
    }
    // For multiple roles change "User" to anything
    private Collection<GrantedAuthority> getAuthorities(List<String> roles) {
        return roles.stream().map(anything -> new SimpleGrantedAuthority("User")).collect(Collectors.toList());
    }

    // Generate Token
    // Validate Token
    // Filter To Intercept and process request
}
