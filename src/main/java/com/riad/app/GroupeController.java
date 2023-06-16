package com.riad.app;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riad.app.entities.clients.Client;
import com.riad.app.entities.clients.CommandeGroupe;
import com.riad.app.entities.clients.Commercial;
import com.riad.app.entities.clients.GroupeClients;
import com.riad.app.entities.stock.Depot;
import com.riad.app.entities.stock.Produit;
import com.riad.app.services.clentsCommercial.ClientService;
import com.riad.app.services.clentsCommercial.CommandeGroupeService;
import com.riad.app.services.clentsCommercial.CommandeService;
import com.riad.app.services.clentsCommercial.CommercialService;
import com.riad.app.services.clentsCommercial.GroupeClientService;
import com.riad.app.services.clentsCommercial.ProduitCommandeService;
import com.riad.app.services.stock.DepotService;
import com.riad.app.services.stock.ProduitService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groupe")
public class GroupeController {
	private final GroupeClientService gcs;
	private final CommercialService comms;
	private final ClientService cs;
	private final CommandeGroupeService cgs;
	private final ProduitService ps;
	private final DepotService ds;
	private final ProduitCommandeService pcs;
	@PostMapping
	public ResponseEntity<GroupeClients> ajouterGroupe(String nomGroupe,double plafond){
		GroupeClients groupe=gcs.ajouterGroupe(nomGroupe, plafond);
		return ResponseEntity.created(URI.create("/groupe"+groupe.getId())).body(groupe);
	}
	@GetMapping("/search")
	public ResponseEntity< List<GroupeClients>> chercher(String mc){
		return ResponseEntity.ok(gcs.chercherGroupeClient(mc));
	}
	@PutMapping("/groupe{idGroupe}")
	public ResponseEntity<GroupeClients> modifierPlafond(@PathVariable Long idGroupe,double plafond){
		gcs.modifierPlafond(idGroupe, plafond);
		return ResponseEntity.ok().build();
	}
	@DeleteMapping("/groupe{id}")
	public ResponseEntity<GroupeClients> supprimer(@PathVariable Long id){
		gcs.supprimerGroupe(id);
		return ResponseEntity.ok().build();
	}
	@GetMapping("/groupe{id}")
	public ResponseEntity<GroupeClients> groupe(@PathVariable Long id){
		return ResponseEntity.ok(gcs.groupeParId(id));
	}
	@PostMapping("/groupe{dGroupe}/cmd")
	public ResponseEntity<GroupeClients> ajouterCommande(@PathVariable Long idGroupe,Long idClient,Long idCommercial){
		GroupeClients groupe=gcs.groupeParId(idGroupe);
		Commercial commercial=comms.commercialParId(idCommercial);
		Client client=cs.clientParId(idCommercial);
		cgs.ajouterCommande(groupe, client, commercial);
		return ResponseEntity.ok().build();
	}
	@PutMapping("/cmd{id}")
	public ResponseEntity<CommandeGroupe> ajouterProduitCommande(@PathVariable Long id,String code,Long idDepot,int qte){
		Produit produit=ps.produitParId(code);
		CommandeGroupe cmdg=cgs.cmdGroupeParId(id);
		Depot depot=ds.depotParId(idDepot);
		pcs.ajouterPCGroupe(cmdg, produit, depot, qte);
		return ResponseEntity.ok().build();
	}
	@PutMapping("cmd{id}/valider")
	public ResponseEntity<CommandeGroupe> valider(@PathVariable Long id){
		cgs.valider(id);
		return ResponseEntity.ok().build();
	}	
	@PutMapping("cmd{id}/anuller")
	public ResponseEntity<CommandeGroupe> anuller(@PathVariable Long id){
		cgs.anuller(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("cmd{id}/payer")
	public ResponseEntity<CommandeGroupe> payerCommande(@PathVariable Long id,double montant){
		cgs.payee(montant, id);
		return ResponseEntity.ok().build();
	}
}
