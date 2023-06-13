package com.riad.app.services.clentsCommercial;

import java.util.List;

import com.riad.app.entities.clients.Commercial;

public interface CommercialService {
	public Commercial commercialParId(Long id);
	public Commercial ajouterCommercial(String nom,String prenom,String email,String username ,String password);
	public Commercial modifierCommercial(Long idCommercial,String nom,String prenom,String email);
	public void supprimerCommercial(Long idCommercial);
	public List<Commercial> chercherCommercial(String mc);
	public Commercial affecterPFCommercial(Long idCommercial,Long idPF);
	
	

}
