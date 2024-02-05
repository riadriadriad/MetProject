package com.riad.app.controllers.restControllers;

import java.net.URI;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riad.app.entities.clients.Client;
import com.riad.app.entities.clients.TypeClient;
import com.riad.app.repositories.clientsCommerciaux.ClientRepository;
import com.riad.app.services.clentsCommercial.ClientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientsController {
	
	private final ClientService cs;
	private final ClientRepository cr;
	@PreAuthorize("hasAuthority('GESTIONNAIRE_COMMERCIAL')")
	@GetMapping
	public ResponseEntity<List<Client>> allclients(){
		return ResponseEntity.ok(cs.allClients());	
	}
	@PreAuthorize("hasAuthority('GESTIONNAIRE_COMMERCIAL')")
	@PostMapping
	public ResponseEntity<Client> ajouterClient(String nom,String prenom,String email,String cin,String adresse,double plafond,TypeClient tc){
		Client client=cs.ajouterClient(nom, prenom, cin, email, adresse, plafond,tc);
		return ResponseEntity.created(URI.create("/client"+client.getId())).body(client);
	}
	
	@GetMapping("/client{id}")
	public ResponseEntity<Client> client(@PathVariable Long id){
		Client client =cs.clientParId(id);
		return ResponseEntity.ok(client);
	}
	@GetMapping("/search")
	public ResponseEntity<List<Client>> chercherClient(String mc){
		List<Client> clients=cs.chercherClient(mc);
		return ResponseEntity.ok(clients);
	}
	
	@PreAuthorize("hasAuthority('GESTIONNAIRE_COMMERCIAL')")
	@PutMapping("/client{id}")
	public ResponseEntity<Client> modifier(@PathVariable Long id,String nom,String prenom,String email,String cin,String adresse,double plafond,TypeClient tc){
		Client client=cs.modifierClient(id, nom, prenom, cin, email, adresse, plafond, tc);
		return ResponseEntity.ok(client);
	}
	
	@PreAuthorize("hasAuthority('GESTIONNAIRE_COMMERCIAL')")
	@DeleteMapping("/client{id}")
	public ResponseEntity<Client> supprimer(@PathVariable Long id){
		cs.supprimerClient(id);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('GESTIONNAIRE_COMMERCIAL')")
	@PutMapping("/client{id}/pf")
	public ResponseEntity<Client> ajouterClientPf(@PathVariable Long id,Long idPf){
		cs.affecterClientPF(id, idPf);
		return ResponseEntity.ok().build();
	}
	@PreAuthorize("hasAuthority('GESTIONNAIRE_COMMERCIAL')")
	@PutMapping("/client{id}/dpf")
	public ResponseEntity<Client> spprimerClientPf(@PathVariable Long id){
		Client cl=cr.findById(id).get();
		cl.setPortefeuille(null);
		cr.save(cl);
		
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('GESTIONNAIRE_COMMERCIAL')")
	@PutMapping("/groupe{idGroupe}/client{idClient}")
		public ResponseEntity<Client> ajouterClientGroupe(@PathVariable Long idGroupe,@PathVariable Long idClient){
		cs.affecterClientGroupe(idClient, idGroupe);
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/pf{id}/clients")
	public ResponseEntity<List<Client>> clientParPF(@PathVariable Long id){
		return ResponseEntity.ok(cs.clientsParPortefeuille(id));
	}
	
}
