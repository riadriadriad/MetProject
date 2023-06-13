package com.riad.app.entities.clients;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.riad.app.entities.clients.Client;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity @NoArgsConstructor @AllArgsConstructor @Data
@Builder
public class GroupeClients {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nomGroupe;
	@Positive
	private double plafond;
	private double credit;
	@ManyToMany
	private List<Client> clients;
	private Status status;
	@JsonManagedReference
	@OneToMany(mappedBy = "groupe")
	private List<CommandeGroupe> commandes; 
}
