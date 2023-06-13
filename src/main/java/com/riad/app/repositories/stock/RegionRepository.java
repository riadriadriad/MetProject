package com.riad.app.repositories.stock;

import java.awt.print.Pageable;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.riad.app.entities.stock.Region;

 public interface RegionRepository extends JpaRepository<Region, Long>,PagingAndSortingRepository<Region,Long> {
 public Region findByNomRegion(String nom);
 public List<Region> findByNomRegionContains(String mc);
}
