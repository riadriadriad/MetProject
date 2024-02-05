package com.riad.app.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderImpl implements PasswordEncoder{
	PasswordEncoder pe=new BCryptPasswordEncoder();
	@Override
	public String encode(CharSequence rawPassword) {
		return pe.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return pe.matches(rawPassword, encodedPassword);
	}

}
