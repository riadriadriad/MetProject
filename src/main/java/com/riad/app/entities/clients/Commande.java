package com.riad.app.entities.clients;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity @NoArgsConstructor @AllArgsConstructor @Data
@Builder
public class Commande {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@JsonBackReference
	@ManyToOne
	private Client client;
	@JsonBackReference
	@ManyToOne
	private Commercial commercial;
	@OneToMany
	private List<ProduitCommande> produits;
	@PositiveOrZero
	private double total;
	@PositiveOrZero
	private double montantPaye;
	@PositiveOrZero
	private double montantReste;
	private boolean validee;
	private boolean anullee;
	private Payee payee;
	
	
	

}
