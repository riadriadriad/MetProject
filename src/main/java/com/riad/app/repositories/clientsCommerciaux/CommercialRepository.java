package com.riad.app.repositories.clientsCommerciaux;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riad.app.entities.clients.Commercial;

public interface CommercialRepository extends JpaRepository<Commercial, Long>{
public List<Commercial> findByNomContainingAndPrenomContaining(String mc,String mcpre);
public Commercial findByUsername(String username);
 }
