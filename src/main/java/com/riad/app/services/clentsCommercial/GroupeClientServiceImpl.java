package com.riad.app.services.clentsCommercial;

import java.util.List;

import org.springframework.stereotype.Service;

import com.riad.app.entities.clients.GroupeClients;
import com.riad.app.entities.clients.Status;
import com.riad.app.repositories.clientsCommerciaux.GroupeClientRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class GroupeClientServiceImpl implements GroupeClientService{
	private final GroupeClientRepository gcr;
	@Override
	public GroupeClients ajouterGroupe(String nomGroupe, double plafond) {
		GroupeClients groupe=GroupeClients.builder()
				.nomGroupe(nomGroupe)
				.plafond(plafond)
				.credit(plafond)
				.status(Status.auth)
				.build();
		return gcr.save(groupe);
	}

	@Override
	public List<GroupeClients> chercherGroupeClient(String mc) {
		return gcr.findByNomGroupeContains(mc);
	}

	@Override
	public GroupeClients modifierGroupe(Long id, GroupeClients groupe) {
		GroupeClients groupeClients=groupeParId(id);
		groupe.setNomGroupe(groupe.getNomGroupe());
		return gcr.save(groupe);
	}

	@Override
	public void supprimerGroupe(Long id) {
		GroupeClients groupe=groupeParId(id);
		gcr.deleteById(id);
	}

	@Override
	public GroupeClients groupeParId(Long id) {
		return gcr.findById(id).get();
	}

	@Override
	public GroupeClients modifierPlafond(Long id, double nvplafond) {
		GroupeClients groupeClients=groupeParId(id);
		double anPlafond=groupeClients.getPlafond();
		groupeClients.setPlafond(nvplafond);
		groupeClients.setCredit(groupeClients.getCredit()+nvplafond-anPlafond);
		if(groupeClients.getCredit()>1000) groupeClients.setStatus(Status.auth);
		if(groupeClients.getCredit()<1000) groupeClients.setStatus(Status.susp);
		return gcr.save(groupeClients);
	}

}
