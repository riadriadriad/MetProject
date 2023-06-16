package com.riad.app.services.clentsCommercial;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.riad.app.entities.clients.ResponsableCommercial;
import com.riad.app.repositories.clientsCommerciaux.ResponsableCommercialRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResponsableComercialServiceImpl implements ResponsableCommercialService{
	private final ResponsableCommercialRepository rcr;
	private final PasswordEncoder pe;
	@Override	
	public ResponsableCommercial ajouterResponsableCommercia(String nom, String prenom, String email, String username,
			String password) {
		ResponsableCommercial respo=ResponsableCommercial.builder()
				.nom(nom)
				.prenom(prenom)
				.email(email)
				.username(username)
				.password(pe.encode(password))
				.role("GC")
				.build();
		return rcr.save(respo);
	}

}
