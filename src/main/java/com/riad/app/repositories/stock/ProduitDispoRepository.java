package com.riad.app.repositories.stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.riad.app.entities.stock.Depot;
import com.riad.app.entities.stock.Produit;
import com.riad.app.entities.stock.ProduitDispo;



public interface ProduitDispoRepository extends JpaRepository<ProduitDispo,Long>{
	public ProduitDispo findByDepotAndProduit(Depot depot,Produit produit);
	public List<ProduitDispo> findByDepot(Depot depot);
	public List<ProduitDispo> findByProduit(Produit produit);
	@Query("select p from ProduitDispo p where p.produit.code= :code and p.depot.numDepot= :id ")
	public ProduitDispo findByBy(String code,Long id);
}
