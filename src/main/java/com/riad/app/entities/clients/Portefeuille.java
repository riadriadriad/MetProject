package com.riad.app.entities.clients;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.riad.app.entities.stock.Region;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity @NoArgsConstructor @AllArgsConstructor @Data
@Builder
public class Portefeuille {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)@NotBlank
	private String nomPortefeuille;
	@JsonManagedReference
	@OneToMany(mappedBy = "portefeuille")
	private List<Client> client;
	@JsonBackReference
	@ManyToOne
	Region region;

}
