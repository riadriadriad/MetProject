package com.riad.app.services.clentsCommercial;

import java.util.List;

import org.springframework.stereotype.Service;

import com.riad.app.entities.clients.Client;
import com.riad.app.entities.clients.GroupeClients;
import com.riad.app.entities.clients.Portefeuille;
import com.riad.app.entities.clients.Status;
import com.riad.app.entities.clients.TypeClient;
import com.riad.app.repositories.clientsCommerciaux.ClientRepository;
import com.riad.app.repositories.clientsCommerciaux.GroupeClientRepository;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{
	private final ClientRepository cr;
	private final PortefeuilleService pfs;
	private final GroupeClientService gs;
	private final GroupeClientRepository gr;
	@Override
	public Client ajouterClient(String nom, String prenom, String cin, String email, String adresse, double plafond,TypeClient tc) {
		Client client =Client.builder()
				.nom(nom)
				.prenom(prenom)
				.cin(cin)
				.email(email)
				.addresse(adresse)
				.plafond(plafond)
				.credit(plafond)
				.tc(tc)
				.status(Status.auth)
				.build();
		return cr.save(client);
	}

	@Override
	public List<Client> chercherClient(String mc) {
		List<Client> clients=cr.findByPrenomContains(mc);
		return clients;
	}

	@Override
	public Client modifierClient(Long id,String nom, String prenom, String cin, String email, String adresse, double plafond,TypeClient tc)  {
		Client cl =clientParId(id);
		cl.setNom(nom);
		cl.setAddresse(adresse);
		cl.setPrenom(prenom);
		cl.setCin(cin);
		cl.setPlafond(plafond);
		cl.setEmail(email);
		cl.setTc(tc);
		return cr.save(cl);
	}

	@Override
	public void supprimerClient(Long id) {
		cr.delete(clientParId(id));
	}

	@Override
	public Client clientParId(Long id) {
		return cr.findById(id).get();
	}

	@Override
	public void affecterClientPF(Long idClient, Long idPF) {
		Client client = clientParId(idClient);
		Portefeuille pf=pfs.pfParId(idPF);
		client.setPortefeuille(pf);
		cr.save(client);
	}

	@Override
	public void affecterClientGroupe(Long idClient, Long idGroupe) {
			GroupeClients groupe=gs.groupeParId(idGroupe);
			Client client =clientParId(idGroupe);
			groupe.getClients().add(client);
			gr.save(groupe);
		
	}

	@Override
	public void supprimerClientPf(Long idClient) {
		Client client=clientParId(idClient);
		client.setPortefeuille(null);
		cr.save(client);
	}

	@Override
	public void supprimerClientGroupe(Long idClient, Long idGroupe) {
		GroupeClients groupe=gs.groupeParId(idGroupe);
		groupe.getClients().remove(clientParId(idGroupe));
		gr.save(groupe);
	}

	@Override
	public List<Client> allClients() {
		return cr.findAll();
	}

	@Override
	public List<Client> clientsParPortefeuille(Long idPf) {
		Portefeuille pf=pfs.pfParId(idPf);
		return pf.getClient();
	}

}
