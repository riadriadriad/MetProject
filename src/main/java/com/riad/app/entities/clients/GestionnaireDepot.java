package com.riad.app.entities.clients;


import com.riad.app.entities.stock.Depot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity @NoArgsConstructor @AllArgsConstructor @Data
@Builder
public class GestionnaireDepot {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="le nom est obligatoire") 
	private String nom;
	@NotBlank(message="le prenom est obligatoire") 
	private String prenom;
	@NotBlank(message="l'email est obligatoire") 
	private String email;
	@OneToOne
	private Depot depot;
	@NotBlank(message="le usrname est obligatoire") 
	@Column(unique=true)
	private String username;
	@NotBlank(message="le mot de passe est obligatoire")
	private String password;
	private String role;

}
