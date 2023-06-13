package com.riad.app.services.clentsCommercial;

import java.util.List;

import com.riad.app.entities.clients.Commande;

public interface CommandeService {
	public Commande commandeParId(Long id);
	public Commande ajouterCommande(Long idClient,Long idCommercial);
	public Commande validerCommande(Long id);
	public Commande anullerCommande(Long id);
	void payerCommande(double montant, Long id);
	public List<Commande> commandesParClient(Long idClient);
	
}
