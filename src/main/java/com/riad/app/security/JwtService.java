package com.riad.app.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class JwtService {
	private final JwtEncoder jwtEncoder;
		
	public  Map<String, String> generateToken(Authentication authentication) {
		Instant instant =Instant.now();
		String scope=authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
		JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
				.subject(authentication.getName())
				.issuedAt(instant)
				.expiresAt(instant.plus(10,ChronoUnit.MINUTES))
				.claim("scope",scope)
				.build();
		JwtEncoderParameters jwtEncoderParameters=JwtEncoderParameters.from(
				JwsHeader.with(MacAlgorithm.HS512).build(),
						jwtClaimsSet);
		String jwt=jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
		return Map.of("jwt",jwt);
	}
	
			}

