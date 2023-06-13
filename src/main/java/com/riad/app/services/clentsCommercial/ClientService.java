package com.riad.app.services.clentsCommercial;

import java.util.List;

import com.riad.app.entities.clients.Client;
import com.riad.app.entities.clients.TypeClient;

public interface ClientService {
	public Client ajouterClient(String nom,String prenom,String cin,String email,String adresse,double plafond,TypeClient tc);
	public List<Client> chercherClient(String mc);
	public void supprimerClient(Long id);
	public Client clientParId(Long id);
	public void affecterClientPF(Long idClient,Long idPF);
	public void affecterClientGroupe(Long idClient,Long idGroupe);
	public void supprimerClientPf(Long idClient);
	public void supprimerClientGroupe(Long idClient,Long idGroupe);
	public List<Client> allClients();
	public List<Client> clientsParPortefeuille(Long idPf);
	Client modifierClient(Long id, String nom, String prenom, String cin, String email, String adresse, double plafond,
			TypeClient tc);
	
	
}
