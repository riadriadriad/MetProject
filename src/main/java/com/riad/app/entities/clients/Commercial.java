package com.riad.app.entities.clients;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @NoArgsConstructor @AllArgsConstructor @Data
@Builder
public class Commercial {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nom;
	@NotBlank
	private String prenom;
	@NotBlank
	private String email;
	@NotBlank
	@Column(unique = true)
	private String username;
	@NotBlank
	private String password;
	@JsonBackReference
	@OneToOne
	private Portefeuille portefeuille;
	@JsonManagedReference
	@OneToMany(mappedBy = "commercial")
	private List<Commande> commandes;
	@JsonManagedReference
	@OneToMany(mappedBy = "commercial")
	private List<CommandeGroupe> cmdGroupe;	
	private String role;
	
}
