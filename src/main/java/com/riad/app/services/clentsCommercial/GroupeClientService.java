package com.riad.app.services.clentsCommercial;

import java.util.List;

import com.riad.app.entities.clients.GroupeClients;

public interface GroupeClientService {
	public GroupeClients ajouterGroupe(String nomGroupe,double plafond);
	public List<GroupeClients> chercherGroupeClient(String mc);
	public GroupeClients modifierGroupe(Long id,GroupeClients groupe);
	public void supprimerGroupe(Long id);
	public GroupeClients groupeParId(Long id);
	public GroupeClients modifierPlafond(Long id,double nvplafond);
	

}
