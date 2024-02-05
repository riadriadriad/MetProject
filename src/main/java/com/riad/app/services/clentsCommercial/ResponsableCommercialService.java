package com.riad.app.services.clentsCommercial;

import com.riad.app.entities.clients.Commercial;
import com.riad.app.entities.clients.ResponsableCommercial;

public interface ResponsableCommercialService {
	public ResponsableCommercial ajouterResponsableCommercia(String nom, String prenom, String email, String username, String password);
	public boolean exist(String username);
	public ResponsableCommercial ByUsername(String username);
}
