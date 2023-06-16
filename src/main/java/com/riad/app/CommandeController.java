package com.riad.app;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riad.app.entities.clients.Commande;
import com.riad.app.entities.stock.Depot;
import com.riad.app.entities.stock.Produit;
import com.riad.app.services.clentsCommercial.CommandeService;
import com.riad.app.services.clentsCommercial.ProduitCommandeService;
import com.riad.app.services.stock.DepotService;
import com.riad.app.services.stock.ProduitService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/commandes")
public class CommandeController {
	private final CommandeService cmds;
	private final ProduitCommandeService pcs;
	private final ProduitService ps;
	private final DepotService ds;
	
	@PreAuthorize("hasAuthority('C')")
	@PostMapping
	public ResponseEntity<Commande> ajouterCommande(Long idClient,Long idCommercial){
		Commande cmd=cmds.ajouterCommande(idClient, idCommercial);
		return ResponseEntity.created(URI.create("/commande"+cmd.getId())).body(cmd);
	}
	
	
	@GetMapping("/client{idClient}/cmds")
	public ResponseEntity<List<Commande>> commandesParClient(@PathVariable Long idClient){
		return ResponseEntity.ok(cmds.commandesParClient(idClient));
	}
	@PreAuthorize("hasAuthority('C')")
	@PostMapping("/cmd{idCommande}")
	public ResponseEntity<Commande> ajouterpProduitCommande(@PathVariable Long idCommande,String code,Long idDepot,int qte){
		Commande cmd=cmds.commandeParId(idCommande);
		Produit produit=ps.produitParId(code);
		Depot depot = ds.depotParId(idDepot);
		pcs.ajouterPC(cmd, produit, depot, qte);
		return ResponseEntity.ok().build();
		
	}
	@PreAuthorize("hasAuthority('GD')")
	@PostMapping("/cmd{idCommande}/valider")
	public ResponseEntity<Commande> valider(@PathVariable Long idCommande){
		cmds.validerCommande(idCommande);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAuthority('GD')")
	@PutMapping("/cmd{idCommande}/anuller")
	public ResponseEntity<Commande> anuller(@PathVariable Long idCommande){
		cmds.anullerCommande(idCommande);
		return ResponseEntity.ok().build();
	}
	@PreAuthorize("hasAuthority('C')")
	@PutMapping("/cmd{idCommande}/payer")
	public ResponseEntity<Commande> payee(Long idCommande,double montant){
		cmds.payerCommande(montant, idCommande);
		return ResponseEntity.ok().build();
	}
}
