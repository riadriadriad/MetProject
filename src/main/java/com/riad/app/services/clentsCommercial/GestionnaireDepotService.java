package com.riad.app.services.clentsCommercial;

import com.riad.app.entities.clients.GestionnaireDepot;

public interface GestionnaireDepotService {

	GestionnaireDepot ajouterGD(String nom, String prenom, String email, String username, String password,
			Long idDepot);
	

}
