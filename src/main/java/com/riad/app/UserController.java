package com.riad.app;

import java.net.URI;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riad.app.entities.clients.Commercial;
import com.riad.app.entities.clients.GestionnaireDepot;
import com.riad.app.entities.clients.ResponsableCommercial;
import com.riad.app.services.clentsCommercial.CommercialService;
import com.riad.app.services.clentsCommercial.GestionnaireDepotService;
import com.riad.app.services.clentsCommercial.ResponsableCommercialService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final CommercialService  cs;
	private final GestionnaireDepotService gds;
	private final ResponsableCommercialService rcs;
	@PreAuthorize("hasAuthority('GC')")
	@PostMapping("/commercial")
	public ResponseEntity<Commercial> ajouterCompteCommercial(String nom,String prenom,String email,String username,String password){
		Commercial commercial=cs.ajouterCommercial(nom, prenom, email, username, password);
		return ResponseEntity.created(URI.create("/commercial"+commercial.getId())).body(commercial);
	}
	@PreAuthorize("hasAuthority('GC')")
	@PutMapping("/commercial{id}")
	public ResponseEntity<Commercial> modifierCommercial(Long id,String nom,String prenom,String email){
		Commercial commercial=cs.modifierCommercial(id, nom, prenom, email);
		return ResponseEntity.ok().build();
	}
	@PreAuthorize("hasAuthority('GC')")
	@PostMapping("/gestionnaireDepot")
	public ResponseEntity<GestionnaireDepot> ajouterCompteGDepot(String nom,String prenom,String email,String username,String password,Long idDepot){
		GestionnaireDepot gd=gds.ajouterGD(nom,prenom,email,username,password,idDepot);
		return ResponseEntity.created(URI.create("/commercial"+gd.getId())).body(gd);
	}
	@PreAuthorize("hasAuthority('GC')")
	@PostMapping("/respo")
	public ResponseEntity<ResponsableCommercial> ajouterCompteGDepot(String nom,String prenom,String email,String username,String password){
		ResponsableCommercial rc=rcs.ajouterResponsableCommercia(nom, prenom, email, username, password);
		return ResponseEntity.created(URI.create("/commercial"+rc.getId())).body(rc);
	}
	
	@PreAuthorize("hasAuthority('GC')")
	@PutMapping("/commercial{id}/pf")
	public ResponseEntity<Commercial> affecterPF(@PathVariable Long id,Long idPf){
		cs.affecterPFCommercial(id, idPf);
		return ResponseEntity.ok().build();
	}

}
