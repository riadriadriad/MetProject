package com.riad.app.services.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riad.app.entities.stock.Region;
import com.riad.app.repositories.stock.RegionRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class RegionServiceImpl implements RegionService {
	@Autowired
	private RegionRepository rr;
	@Override
	public Region ajouterRegion(String nom) {
		Region region=Region.builder()
				.nomRegion(nom)
				.build();
		return rr.save(region);
	}

	@Override
	public void supprimerRegion(String nom) {
		rr.delete(rr.findByNomRegion(nom));
		
	}

	@Override
	public List<Region> allRegions() {
		return rr.findAll();
	}

	@Override
	public Region regionParId(Long id) {
		return rr.findById(id).get();
	}

/*	@Override
	public List<Region> chercherRegion(String mc) {
		return rr.findByNomRegionContains(mc);} */
	

}
