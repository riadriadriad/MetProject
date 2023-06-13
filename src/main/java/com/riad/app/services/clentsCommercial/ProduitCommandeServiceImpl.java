package com.riad.app.services.clentsCommercial;

import org.springframework.stereotype.Service;

import com.riad.app.entities.clients.Commande;
import com.riad.app.entities.clients.CommandeGroupe;
import com.riad.app.entities.clients.ProduitCommande;
import com.riad.app.entities.clients.Status;
import com.riad.app.entities.stock.Depot;
import com.riad.app.entities.stock.Produit;
import com.riad.app.entities.stock.ProduitDispo;
import com.riad.app.repositories.clientsCommerciaux.ProduitCommandeRepository;
import com.riad.app.services.stock.ProduitDispoService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ProduitCommandeServiceImpl implements ProduitCommandeService {
	private final ProduitCommandeRepository pcr;
	private final ProduitDispoService pds;
	@Override
	public ProduitCommande pcParId(Long id) {
		return pcr.findById(id).get();
	}


	@Override
	public ProduitCommande modifier(Long id, int qte) {
		ProduitCommande pc=pcParId(id);
		pc.setQte(qte);
		return pcr.save(pc);
	}

	@Override
	public void supprimer(Long id) {
		pcParId(id);
		pcr.deleteById(id);
	}


	@Override
	public ProduitCommande ajouterPC(Commande commande, Produit produit, Depot depot, int qte) {
		ProduitDispo pdispo=pds.produitDepot(produit, depot);
		double montant=qte*produit.getPrix();
		if(qte<=pdispo.getQuant() && commande.getClient().getStatus()==Status.auth && !commande.isValidee() && !commande.isAnullee()) {
			if(montant+commande.getTotal()<=commande.getClient().getCredit()) {
				ProduitCommande pc=ProduitCommande.builder()
						.commande(commande)
						.produit(produit)
						.depot(depot)
						.qte(qte)
						.build();
				commande.setTotal(montant+commande.getTotal());
				commande.setMontantReste(commande.getMontantReste()+montant);
				return pcr.save(pc);
				
				
			}
		}
		
		return null;
	}


	@Override
	public ProduitCommande ajouterPCGroupe(CommandeGroupe commande, Produit produit, Depot depot, int qte) {
		ProduitDispo pdispo=pds.produitDepot(produit, depot);
		double montant=qte*produit.getPrix();
		if(qte>=pdispo.getQuant() && commande.getGroupe().getStatus()!=Status.auth && !commande.isValidee() && !commande.isAnullee()) {
			if(montant+commande.getTotal()<=commande.getGroupe().getCredit()) {
				ProduitCommande pc=ProduitCommande.builder()
						.cmdGroupe(commande)
						.produit(produit)
						.depot(depot)
						.qte(qte)
						.build();
				commande.setTotal(montant+commande.getTotal());
				commande.setMontantReste(commande.getMontantReste()+montant);
				return pcr.save(pc);
				
				
			}
		}
		
		return null;
		
	}

}
