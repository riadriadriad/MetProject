package com.riad.app.services.clentsCommercial;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.riad.app.entities.clients.Commercial;
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
				.build();
		return rcr.save(respo);
	}

	@Override
	public boolean exist(String username) {
		List<ResponsableCommercial> respos=rcr.findAll();
		for(ResponsableCommercial r:respos) {
			if(r.getUsername().equals(username)) return true;
		}
		return false;
	}

	@Override
	public ResponsableCommercial ByUsername(String username) {
		if(exist(username)) return rcr.findByUsername(username);
		else {
			throw new UsernameNotFoundException("user not found");
		}
	}
}
