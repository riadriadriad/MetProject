package com.riad.app.repositories.clientsCommerciaux;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riad.app.entities.clients.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
public List<Client> findByNomContainingAndPrenomContaining(String mc,String mcPrenom);
public List<Client> findByPrenomContains(String mc);
}
