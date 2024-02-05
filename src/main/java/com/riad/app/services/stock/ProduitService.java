package com.riad.app.services.stock;

import java.util.List;

import com.riad.app.entities.stock.FamilleProduit;
import com.riad.app.entities.stock.Produit;


public interface ProduitService {
	public List <Produit> findAllProds();
	public Produit ajouterProduit(Produit produit);
	public void supprimerProduit(String code);
	public void modifierProduit(Produit produit);
	public List<Produit> chercherProduit(String mc);
	public Produit produitParId(String code);
	public String generateCode(Produit produit);
	
	

}
