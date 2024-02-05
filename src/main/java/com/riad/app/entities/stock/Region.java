package com.riad.app.entities.stock;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @NoArgsConstructor @AllArgsConstructor @Data
@Builder
public class Region {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numRegion;
	@Column(unique=true,length=191)
	private String nomRegion;
	@OneToMany(mappedBy="region")
	private List<Depot> depots;
}
