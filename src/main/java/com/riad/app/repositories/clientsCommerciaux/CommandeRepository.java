package com.riad.app.repositories.clientsCommerciaux;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riad.app.entities.clients.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long>{

}
