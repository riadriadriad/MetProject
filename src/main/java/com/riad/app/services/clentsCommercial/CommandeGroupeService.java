package com.riad.app.services.clentsCommercial;

import com.riad.app.entities.clients.Client;
import com.riad.app.entities.clients.CommandeGroupe;
import com.riad.app.entities.clients.Commercial;
import com.riad.app.entities.clients.GroupeClients;

public interface CommandeGroupeService {
	public CommandeGroupe cmdGroupeParId(Long id);
	public CommandeGroupe ajouterCommande(GroupeClients groupe,Client client,Commercial commercial);
	public void supprimer(Long id);
	public void valider(Long id);
	public void anuller(Long id);
	void payee(double montant, Long id);
	
	

}
