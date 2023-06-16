package com.riad.app.repositories.stock;

import java.awt.print.Pageable;
import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.riad.app.entities.stock.Produit;
@CrossOrigin("*")
@RepositoryRestResource
public interface ProduitRepository extends JpaRepository<Produit, String>,PagingAndSortingRepository<Produit, String>{
	
	public List<Produit> findByCodeContains(String mc);
	@Query("select p from Produit p where p.nomProduit like %:mc% ")
	public List<Produit> searchByName(@Param("mc") String mc);
	
	public List<Produit> findByNomProduitContains( String mc);
}
