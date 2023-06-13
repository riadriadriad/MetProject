package com.riad.app.services.clentsCommercial;

import java.util.List;

import org.springframework.stereotype.Service;

import com.riad.app.entities.clients.Client;
import com.riad.app.entities.clients.CommandeGroupe;
import com.riad.app.entities.clients.Commercial;
import com.riad.app.entities.clients.GroupeClients;
import com.riad.app.entities.clients.Payee;
import com.riad.app.entities.clients.ProduitCommande;
import com.riad.app.entities.clients.Status;
import com.riad.app.repositories.clientsCommerciaux.CommandeGroupeRepository;
import com.riad.app.repositories.clientsCommerciaux.GroupeClientRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CommandeGroupeServiceImpl implements CommandeGroupeService{
	private final CommandeGroupeRepository cgr;
	private final GroupeClientRepository gcr;
	private final GroupeClientService gcs;
	
	@Override
	public CommandeGroupe cmdGroupeParId(Long id) {
		
		return cgr.findById(id).get();
	}

	@Override
	public CommandeGroupe ajouterCommande(GroupeClients groupe, Client client, Commercial commercial) {
	
		if(client.getStatus()==Status.susp) {
		CommandeGroupe cg=CommandeGroupe.builder()
				.groupe(groupe)
				.client(client)
				.commercial(commercial)
				.validee(false)
				.anullee(false)
				.payee(Payee.nonPayee)
				.build();
		return cgr.save(cg);
	}

			return null;
	}

	@Override
	public void supprimer(Long id) {
		cgr.delete(cmdGroupeParId(id));
	}

	@Override
	public void valider(Long id) {
		CommandeGroupe cmd=cmdGroupeParId(id);
		GroupeClients groupe=cmd.getGroupe();
		groupe.setCredit(groupe.getCredit()-cmd.getTotal());
		if(groupe.getCredit()<1000) groupe.setStatus(Status.susp);
		cmd.setValidee(true);
		cmd.setAnullee(false);
		gcr.save(groupe);
		cgr.save(cmd);
	}

	@Override
	public void anuller(Long id) {
		CommandeGroupe cmd=cmdGroupeParId(id);
		cmd.setValidee(false);
		cmd.setAnullee(true);
		cgr.save(cmd);
	}

	public double calculerTotal(Long idCmdGroupe) {
		CommandeGroupe cmd=cmdGroupeParId(idCmdGroupe);
		List<ProduitCommande>  produits=cmd.getProduitCommandes();
		double total=0;
		for(ProduitCommande p:produits) {
			total=total+p.getQte()*p.getProduit().getPrix();
		}
		cmd.setTotal(total);
		cmd.setMontantReste(total-cmd.getMontantPayee());
		cgr.save(cmd);
		return total;	
	}
	
	
	@Override
	public void payee(double montant,Long id) {
		CommandeGroupe cmd=cmdGroupeParId(id);
		GroupeClients groupe=cmd.getGroupe();
		if(montant<cmd.getMontantReste()) {
			cmd.setMontantPayee(montant+cmd.getMontantPayee());
			cmd.setMontantReste(cmd.getMontantReste()-montant);
			groupe.setCredit(groupe.getCredit()+montant);
			if(groupe.getCredit()>1000) groupe.setStatus(Status.auth);
			if(cmd.getMontantReste()==0) { cmd.setPayee(Payee.payee);}
			else {
				cmd.setPayee(Payee.partiellementPayee);
			}
			
		}
		gcr.save(groupe);
		cgr.save(cmd);
	}

}
