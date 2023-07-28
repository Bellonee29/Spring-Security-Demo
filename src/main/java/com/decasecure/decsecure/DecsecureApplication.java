package com.decasecure.decsecure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DecsecureApplication {


	@GetMapping("/here")
	public String gotHere() {
		return "I reach here o!";
	}
	// Secure

	//FILTER (FilterChain) **

	// Authentication && Authorization
	// Authentication -- Know the Person -- 401 -- Unauthorized
	// Authorization -- What they can Access -- 403 -- No Access

	// Journey (o_o) Login --> /login --> Authenticate(check details) --> Generate and Token and Send to User
	// /paybill --> provide Token --> Filter will check Token Secret and Email --> if valid ? -->
	// continue chain




//	1.
//	FilterChain
//	Do we have to Authenticate?
//	Yes
//	2.
//	AuthenticationManager
//	3.
//	AuthenticationProvider (Jwt)
//	4. UserDetails
//	5. Save User in SpringSecurityContext and DoFilter(req, res)
//	6. Controller





	public static void main(String[] args) {
		SpringApplication.run(DecsecureApplication.class, args);
	}

}
