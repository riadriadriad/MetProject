package com.riad.app.services.clentsCommercial;


import java.util.List;

import com.riad.app.entities.clients.Portefeuille;
import com.riad.app.entities.stock.Region;

public interface PortefeuilleService {
	public Portefeuille pfParId(Long id);
	public Portefeuille ajouterPF(String nomPortfeuille,Region region);
	public List<Portefeuille> chercherPortfeuille(String mc);
}
