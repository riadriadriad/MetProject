package com.riad.app.repositories.clientsCommerciaux;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riad.app.entities.clients.Commercial;
import com.riad.app.entities.clients.ResponsableCommercial;

public interface ResponsableCommercialRepository extends JpaRepository<ResponsableCommercial, Long> {
	public ResponsableCommercial findByUsername(String username);

}
