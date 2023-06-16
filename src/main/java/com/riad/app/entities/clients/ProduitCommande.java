package com.riad.app.entities.clients;

import com.riad.app.entities.stock.Depot;
import com.riad.app.entities.stock.Produit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity @NoArgsConstructor @AllArgsConstructor @Data
@Builder
public class ProduitCommande {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Produit produit;
	@ManyToOne
	private Commande commande;
	@Positive
	private int qte;
	@ManyToOne
	private Depot depot;
	@ManyToOne
	private CommandeGroupe cmdGroupe;
}
