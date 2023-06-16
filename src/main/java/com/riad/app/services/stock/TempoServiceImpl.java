package com.riad.app.services.stock;

import org.springframework.stereotype.Service;

import com.riad.app.entities.stock.Depot;
import com.riad.app.entities.stock.Produit;
import com.riad.app.entities.stock.TempoStock;
import com.riad.app.repositories.stock.TempoStockRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class TempoServiceImpl implements TempoStockService{
	private final TempoStockRepository tsr;
	@Override
	public TempoStock create(Depot depot, Produit produit, int qte) {
		TempoStock ts=TempoStock.builder()
				.depot(depot)
				.produit(produit)
				.qte(qte)
				.build();
		return tsr.save(ts);
	}

	@Override
	public TempoStock tempoStockParId(Long id) {
		
		return tsr.findById(id).get();
	}

	@Override
	public void supprimerTempoStock(Long id) {
		tempoStockParId(id);
		tsr.deleteById(id);
	}

}
