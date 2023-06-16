package com.riad.app.services.stock;

import java.util.List;

import com.riad.app.entities.stock.FamilleProduit;


public interface FPService {
	public FamilleProduit ajouterFP(String lib);
	public void supprimerFP(FamilleProduit familleProduit);
	public FamilleProduit modifierFamille(Long id ,String lib);
	public List<FamilleProduit> allFams();
	public List<FamilleProduit> chercherFamilleProduit(String mc);
	public FamilleProduit familleParId(Long id);
	
}
