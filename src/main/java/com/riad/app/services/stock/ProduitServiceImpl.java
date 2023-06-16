package com.riad.app.services.stock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riad.app.entities.stock.FamilleProduit;
import com.riad.app.entities.stock.Produit;
import com.riad.app.repositories.stock.ProduitRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class ProduitServiceImpl implements ProduitService{
	@Autowired
	private ProduitRepository pr;
	@Override
	public List<Produit> findAllProds() {
		 return pr.findAll();
	}
	@Override
	public Produit ajouterProduit(Produit produit) {
		
		return pr.save(produit);
		
	}
	@Override
	public void supprimerProduit(String code) {
		pr.deleteById(code);
	}
	@Override
	public void modifierProduit(Produit produit) {
		
		pr.save(produit);
	}
	
	public List<Produit> chercherProduit(String mc) {
		List<Produit> prods=new ArrayList<Produit>();
		prods.addAll(pr.findByCodeContains(mc));
		prods.addAll(pr.findByNomProduitContains(mc));
		return prods;
	}
	@Override
	public Produit produitParId(String code) {
		
		return pr.findById(code).get();
	}

}
