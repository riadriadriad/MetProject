package com.riad.app.services.stock;

import java.util.List;

import com.riad.app.entities.stock.Region;


public interface RegionService {
	public Region ajouterRegion(String nom);
	public void supprimerRegion(String nom);
	public List<Region> allRegions();
	public Region regionParId(Long id);
	//public List<Region> chercherRegion(String mc);
}
