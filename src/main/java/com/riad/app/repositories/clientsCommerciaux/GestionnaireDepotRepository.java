package com.riad.app.repositories.clientsCommerciaux;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riad.app.entities.clients.Commercial;
import com.riad.app.entities.clients.GestionnaireDepot;

public interface GestionnaireDepotRepository extends JpaRepository<GestionnaireDepot, Long> {
	public GestionnaireDepot findByUsername(String username);

}
