package com.riad.app.services.clentsCommercial;

import com.riad.app.entities.clients.Commande;
import com.riad.app.entities.clients.CommandeGroupe;
import com.riad.app.entities.clients.ProduitCommande;
import com.riad.app.entities.stock.Depot;
import com.riad.app.entities.stock.Produit;

public interface ProduitCommandeService {
	public ProduitCommande pcParId(Long id);
	public ProduitCommande ajouterPC(Commande commande ,Produit produit,Depot depot,int qte);
	public ProduitCommande ajouterPCGroupe(CommandeGroupe commande ,Produit produit,Depot depot,int qte);
	public ProduitCommande modifier(Long id,int qte);
	public void supprimer(Long id);

}
