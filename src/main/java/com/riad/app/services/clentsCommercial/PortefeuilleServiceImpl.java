package com.riad.app.services.clentsCommercial;

import java.util.List;

import org.springframework.stereotype.Service;

import com.riad.app.entities.clients.Portefeuille;
import com.riad.app.entities.stock.Region;
import com.riad.app.repositories.clientsCommerciaux.PortefeuilleRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class PortefeuilleServiceImpl implements PortefeuilleService{
	private final PortefeuilleRepository pfr;
	@Override
	public Portefeuille pfParId(Long id) {		
		return pfr.findById(id).get();
	}

	@Override
	public Portefeuille ajouterPF(String nomPortfeuille, Region region) {
		Portefeuille pf=Portefeuille.builder()
				.nomPortefeuille(nomPortfeuille)
				.region(region)
				.build();
		return pfr.save(pf);
	}

	@Override
	public List<Portefeuille> chercherPortfeuille(String mc) {
		return pfr.findByNomPortefeuilleContains(mc);
	}
	
}
