package com.riad.app.repositories.clientsCommerciaux;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riad.app.entities.clients.GroupeClients;

public interface GroupeClientRepository extends JpaRepository<GroupeClients, Long>{
public List<GroupeClients> findByNomGroupeContains(String mc);
}
