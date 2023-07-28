package com.decasecure.decsecure.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.JaasAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class OurFilter extends OncePerRequestFilter {

    @Autowired
    private TokenHouse tokenHouse;

    @Autowired
    private MyUserService myUserService;

    // Plug to the FilterChain
    // Intercept Request
    // Extract Token
    // Validate Token
    // Continue

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Open Login and Register
        // check the route and continue if Login or Register

        String url = request.getRequestURI();

        if (Objects.equals(url, "/auth/login") || Objects.equals(url, "/auth/register")) {
            log.info("user wants to login or register");
            filterChain.doFilter(request, response);
            return;
        }


        String tokenHeader = request.getHeader("Authorization");
        String token = tokenHeader.substring(7);

        String email = tokenHouse.getUserNameFromToken(token);
        boolean isTokenValid = tokenHouse.validateToken(token);

        if (isTokenValid) {
            UserDetails userDetails = myUserService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
