package com.riad.app.repositories.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riad.app.entities.stock.TempoStock;

public interface TempoStockRepository extends JpaRepository<TempoStock, Long>{

}
