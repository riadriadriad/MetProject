package com.riad.app.services.clentsCommercial;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.riad.app.entities.clients.Commercial;
import com.riad.app.entities.clients.GestionnaireDepot;
import com.riad.app.entities.clients.ResponsableCommercial;
import com.riad.app.entities.stock.Depot;
import com.riad.app.repositories.clientsCommerciaux.GestionnaireDepotRepository;
import com.riad.app.services.stock.DepotService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class GestionnaireDepotServiceImpl implements GestionnaireDepotService{
	private final GestionnaireDepotRepository gdr;
	private final DepotService ds;
	private final PasswordEncoder pe;
	@Override
	public GestionnaireDepot ajouterGD(String nom, String prenom, String email, String username, String password,
			Long idDepot) {
		Depot depot=ds.depotParId(idDepot);
		
		GestionnaireDepot gd=GestionnaireDepot.builder()
				.nom(prenom)
				.prenom(prenom)
				.email(email)
				.username(username)
				.password(pe.encode(password))
				.depot(depot)
				.build();
		return gdr.save(gd);
	}

	@Override
	public boolean exist(String username) {
		List<GestionnaireDepot> gestionnairesDepot=gdr.findAll();
		for(GestionnaireDepot g:gestionnairesDepot) {
			if(g.getUsername().equals(username)) return true;
		}
		return false;
	}

	@Override
	public GestionnaireDepot ByUsername(String username) {
		if(exist(username)) return gdr.findByUsername(username);
		else {
			throw new UsernameNotFoundException("user not found");
		}
	}
}
