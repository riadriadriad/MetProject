package com.riad.app.services.clentsCommercial;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.riad.app.entities.clients.Commercial;
import com.riad.app.entities.clients.Portefeuille;
import com.riad.app.repositories.clientsCommerciaux.CommercialRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CommercialServiceImpl implements CommercialService{
	private final PasswordEncoder pe;
	private final CommercialRepository commr;
	private final PortefeuilleService pfs;
	@Override
	public Commercial commercialParId(Long id) {
		return commr.findById(id).get();
	}

	@Override
	public Commercial ajouterCommercial(String nom, String prenom, String email, String username, String password) {
		Commercial commercial=Commercial.builder()
				.nom(nom)
				.prenom(prenom)
				.email(email)
				.username(username)
				.password(pe.encode(password))
				.role("C")
				.build();
		return commr.save(commercial);
	}

	@Override
	public Commercial modifierCommercial(Long idCommercial, String nom,String prenom,String email) {
		Commercial comm=commercialParId(idCommercial);
		comm.setNom(nom);
		comm.setPrenom(prenom);
		comm.setEmail(email);
		return commr.save(comm);
	}

	@Override
	public void supprimerCommercial(Long idCommercial) {
		commr.deleteById(idCommercial);
	}

	@Override
	public List<Commercial> chercherCommercial(String mc) {
		List<Commercial> comms=commr.findByNomContainingAndPrenomContaining(mc, mc);
		return  comms;
	}

	@Override
	public Commercial affecterPFCommercial(Long idCommercial, Long idPF) {
		Commercial comm=commercialParId(idCommercial);
		Portefeuille pf=pfs.pfParId(idPF);
		comm.setPortefeuille(pf);
		return commr.save(comm);
	}

}
