package com.riad.app.services.stock;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riad.app.entities.stock.FamilleProduit;
import com.riad.app.repositories.stock.FPRep;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@Service
@Transactional
@RequiredArgsConstructor
public class FPServImpl implements FPService {
	
	private final FPRep fprep;
	@Override
	public FamilleProduit ajouterFP(String lib) {
		FamilleProduit familleProduit=FamilleProduit.builder()
				.libille(lib).build();
		fprep.save(familleProduit);
		
		return familleProduit;
	}
	public void supprimerFP(FamilleProduit familleProduit) {
		fprep.delete(familleProduit);
		
	}
	@Override
	public List<FamilleProduit> allFams() {
		
		return fprep.findAll();
	}
	@Override
	public FamilleProduit modifierFamille(Long id, String lib) {
		FamilleProduit fp=fprep.findById(id).get();
		fp.setLibille(lib);
		return fprep.save(fp);
	}
	@Override
	public List<FamilleProduit> chercherFamilleProduit(String mc) {
		return fprep.findByLibilleContains(mc);
		
	}
	@Override
	public FamilleProduit familleParId(Long id) {
		return fprep.findById(id).get();
	}
	

	

}
