package com.riad.app.repositories.stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.riad.app.entities.stock.Depot;

public interface DepotRepository extends JpaRepository<Depot, Long> {
public List<Depot> findByAdresseContains(String mc);
}
