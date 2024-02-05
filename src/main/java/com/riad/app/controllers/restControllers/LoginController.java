package com.riad.app.controllers.restControllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.riad.app.dtos.AuthenticationRequest;
import com.riad.app.security.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class LoginController {
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	
	@PostMapping("/login")
	public Map<String,String> login(@RequestBody AuthenticationRequest auth){
		Authentication authentication=authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(auth.getUsername(),auth.getPassword()));  
		return jwtService.generateToken(authentication); 
	
	}
	@GetMapping("/profile")
	public Object authentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
}
