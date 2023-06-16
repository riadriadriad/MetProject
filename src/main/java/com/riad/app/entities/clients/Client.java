package com.riad.app.entities.clients;

import java.util.List;

import org.springframework.boot.context.properties.bind.DefaultValue;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.riad.app.entities.stock.Depot;
import com.riad.app.entities.stock.ProduitDispo;
import com.riad.app.entities.stock.Region;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @NoArgsConstructor @AllArgsConstructor @Data
@Builder
public class Client {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Le nom est obligatoire")
	private String nom;
	@NotBlank(message = "Le prenom est obligatoire")
	private String prenom;
	@Column(unique = true)
	@NotBlank(message = "Le CIN est obligatoire")
	private String cin;
	@Column(unique = true)
	@NotBlank(message = "L'email est obligatoire")
	private String email;
	private String addresse;
	private Status status;
	@Positive
	private double plafond;
	@PositiveOrZero
	private double credit;
	@JsonManagedReference
	@OneToMany(mappedBy = "client")
	List<Commande> commandes;
	@JsonBackReference
	@ManyToOne
	private Portefeuille portefeuille;
	private TypeClient tc;

}
