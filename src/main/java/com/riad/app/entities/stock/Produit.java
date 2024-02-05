package com.riad.app.entities.stock;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity @NoArgsConstructor @AllArgsConstructor @Data
@Builder
public class Produit {
	@Id 
	@Column(length=191) @NotBlank
	private String code;
	@Column(length=191) @NotBlank
	private String nomProduit;
	 @Positive
	private double prix;
	 @JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	private FamilleProduit famille;
	@JsonBackReference
	@OneToMany(mappedBy="produit")
	private List<ProduitDispo> produitDispo;
	
	
}
