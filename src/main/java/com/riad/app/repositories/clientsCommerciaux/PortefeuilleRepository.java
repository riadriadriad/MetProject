package com.riad.app.repositories.clientsCommerciaux;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riad.app.entities.clients.Portefeuille;

public interface PortefeuilleRepository extends JpaRepository<Portefeuille, Long>{
public List<Portefeuille> findByNomPortefeuilleContains(String mc);
}
