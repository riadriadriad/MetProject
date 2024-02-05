package com.riad.app.security;

import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import com.nimbusds.jose.jwk.source.ImmutableSecret;

import com.riad.app.services.clentsCommercial.CommercialService;
import com.riad.app.services.clentsCommercial.ResponsableCommercialService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecConfig  {
	@Value("${jwt.secretKey}")
	private String jwtSecret;
	private final CommercialService commercialService;
	private final ResponsableCommercialService responsableCommercialService;
	private final CommercialService gestiDepotService;
	private final PasswordEncoder passwordEncoder;
	
	  @Bean
	  UserDetailsService userDetailsService() {
				return new UserDetailsService() {
					

					@Override
					public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
						if(commercialService.exist(username)) return commercialService.ByUsername(username);
						if(responsableCommercialService.exist(username)) return responsableCommercialService.ByUsername(username);
						if(gestiDepotService.exist(username)) return gestiDepotService.ByUsername(username);
						throw new UsernameNotFoundException("user not found");
					}
				};}
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
		
	}
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService());
		auth.setPasswordEncoder(passwordEncoder);
		return auth;
	}
	
	@Bean
	SecurityFilterChain filter(HttpSecurity http) throws Exception {
			return	http.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
				csrf(csrf->csrf.disable())
				.cors(Customizer.withDefaults())
				.authorizeHttpRequests(ar->ar.requestMatchers("/","/login/**","/graphql/**").permitAll())
				.authorizeHttpRequests(ar->ar.anyRequest().permitAll())
				.oauth2ResourceServer(oa->oa.jwt(Customizer.withDefaults()))
				.build();
	}	
	@Bean
	JwtEncoder jwtEncoder() {
		return new NimbusJwtEncoder(new ImmutableSecret<>(jwtSecret.getBytes()));
	}
	@Bean
	JwtDecoder  jwtDecoder() {
		SecretKeySpec secretKeySpec=new SecretKeySpec(jwtSecret.getBytes(), "HS512");
		return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
		
	}
	@Bean
	CorsConfigurationSource configurationSource() {
		CorsConfiguration corsConfiguration=new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.setExposedHeaders(List.of("x-auth-token"));
		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
	
	

}
