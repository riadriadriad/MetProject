package com.riad.app.services.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riad.app.entities.stock.Depot;
import com.riad.app.entities.stock.Region;
import com.riad.app.repositories.stock.DepotRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class DepotServiceImpl implements DepotService {
	@Autowired
	private DepotRepository dr;
	@Override
	public Depot ajouterDepot(Region region, String adresse) {
		Depot depot =Depot.builder()
				.adresse(adresse)
				.region(region)
				.build();
		return dr.save(depot);
	}

	@Override
	public void supprimerDepot(Long id) {
		dr.deleteById(id);
	}

	@Override
	public void modifierDepot(Long id, String addresse) {
		Depot depot=dr.findById(id).get();
		depot.setAdresse(addresse);
		dr.save(depot);
		
	}

	@Override
	public List<Depot> allDepots() {
		return dr.findAll();
	}

	@Override
	public List<Depot> chercherDepot(String mc) {
		return dr.findByAdresseContains(mc);
	}

	@Override
	public Depot depotParId(Long id) {
		return dr.findById(id).get();
	
	}

}
