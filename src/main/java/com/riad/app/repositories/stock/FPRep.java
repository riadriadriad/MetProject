package com.riad.app.repositories.stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.riad.app.entities.stock.FamilleProduit;

public interface FPRep extends JpaRepository<FamilleProduit, Long> {
public List<FamilleProduit> findByLibilleContains(String kw);
}
