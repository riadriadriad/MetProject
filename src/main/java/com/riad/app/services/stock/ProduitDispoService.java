package com.riad.app.services.stock;

import java.util.List;

import com.riad.app.entities.stock.Depot;
import com.riad.app.entities.stock.Produit;
import com.riad.app.entities.stock.ProduitDispo;

import jakarta.validation.constraints.Positive;

public interface ProduitDispoService {
	public ProduitDispo ajouterProduitDispo(Produit produit,Depot depot,int qte);
	public void retirerQteProduit(Produit produit ,Depot depot ,@Positive int qte);
	public void ajouterQteProduit(Produit produit,Depot depot ,@Positive int qte);
	public void supprimerProduitDispo(Long id);
	public List<ProduitDispo> produitsDepot(Depot depot);
	public List<ProduitDispo> produitStock(Produit produit);
	public ProduitDispo produitDepot(Produit produit,Depot depot);
}
