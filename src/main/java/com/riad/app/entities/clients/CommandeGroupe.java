package com.riad.app.entities.clients;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity @NoArgsConstructor @AllArgsConstructor @Data
@Builder
public class CommandeGroupe {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Commercial commercial;
	@ManyToOne
	private GroupeClients groupe;
	@ManyToOne
	private Client client;
	@OneToMany(mappedBy = "cmdGroupe")
	private List<ProduitCommande> produitCommandes;
	private boolean validee;
	private boolean anullee;
	private double total;
	private double montantPayee;
	private double montantReste;
	private Payee payee;
}
