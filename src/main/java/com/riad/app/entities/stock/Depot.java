package com.riad.app.entities.stock;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @NoArgsConstructor @AllArgsConstructor @Data
@Builder
public class Depot {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numDepot;
	@Column(length=191) @NotBlank
	private String adresse;
	@ManyToOne 
	@JsonBackReference
	private Region region;
	@JsonManagedReference
	@OneToMany(mappedBy="depot")
	private List<ProduitDispo> produitDispo;
	

}
