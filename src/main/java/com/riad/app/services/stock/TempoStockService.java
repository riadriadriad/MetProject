package com.riad.app.services.stock;

import com.riad.app.entities.stock.Depot;
import com.riad.app.entities.stock.Produit;
import com.riad.app.entities.stock.TempoStock;

public interface TempoStockService {
	public TempoStock create(Depot depot ,Produit produit,int qte);
	public TempoStock tempoStockParId(Long id);
	public void supprimerTempoStock(Long id);

}
