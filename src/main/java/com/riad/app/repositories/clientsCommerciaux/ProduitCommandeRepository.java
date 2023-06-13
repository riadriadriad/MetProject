package com.riad.app.repositories.clientsCommerciaux;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riad.app.entities.clients.ProduitCommande;

public interface ProduitCommandeRepository extends JpaRepository<ProduitCommande, Long>{
	

}
