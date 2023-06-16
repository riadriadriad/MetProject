package com.riad.app.services.clentsCommercial;

import java.util.List;

import org.springframework.stereotype.Service;

import com.riad.app.entities.clients.Client;
import com.riad.app.entities.clients.Commande;
import com.riad.app.entities.clients.Commercial;
import com.riad.app.entities.clients.Payee;
import com.riad.app.entities.clients.ProduitCommande;
import com.riad.app.entities.clients.Status;
import com.riad.app.entities.stock.Depot;
import com.riad.app.entities.stock.Produit;
import com.riad.app.entities.stock.ProduitDispo;
import com.riad.app.repositories.clientsCommerciaux.ClientRepository;
import com.riad.app.repositories.clientsCommerciaux.CommandeRepository;
import com.riad.app.repositories.stock.DepotRepository;
import com.riad.app.repositories.stock.ProduitDispoRepository;
import com.riad.app.repositories.stock.ProduitRepository;
import com.riad.app.services.stock.ProduitDispoService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService{
	private final CommandeRepository cmdr;
	private final ClientService cs;
	private final CommercialService comms;
	private final ClientRepository cr;
	private final ProduitDispoService pds;
	private final ProduitDispoRepository pdr;
	private final ProduitRepository pr;
	private final DepotRepository dr;
	@Override
	public Commande commandeParId(Long id) {

		return cmdr.findById(id).get();
	}

	@Override
	public Commande ajouterCommande(Long idClient, Long idCommercial) {
		Client client=cs.clientParId(idCommercial);
		Commercial commercial=comms.commercialParId(idCommercial);
		if(client.getStatus()==Status.auth && commercial.getPortefeuille().getClient().contains(client)) {
			Commande commande=Commande.builder()
				.commercial(commercial)
				.client(client)
				.payee(Payee.nonPayee)
				.anullee(false)
				.validee(false)
				.build();
		return cmdr.save(commande);
		}
		return null;
	}

	@Override
	public Commande validerCommande(Long id) {
		Commande cmd=cmdr.findById(id).get();
		List<ProduitCommande> pcs=cmd.getProduits();
		Client cl=cmd.getClient();
		if( !cmd.isValidee()) {
			cl.setCredit(cl.getCredit()-cmd.getTotal());
			cmd.setPayee(Payee.nonPayee);
			if(cl.getCredit()<1000) {
				cl.setStatus(Status.susp);
				cr.save(cl);
			}
			for(ProduitCommande p:pcs ) {
			pds.retirerQteProduit(p.getProduit(), p.getDepot(), p.getQte());
			}
		}
		cmd.setValidee(true);
	return cmdr.save(cmd);
	}

	@Override
	public Commande anullerCommande(Long id) {
		Commande cmd=commandeParId(id);
		cmd.setValidee(false);
		cmd.setAnullee(true);
		return cmdr.save(cmd);
	}

	@Override
	public void payerCommande(double montant,Long id) {
		Commande cmd=commandeParId(id);
		Client client=cmd.getClient();
		if(cmd.getPayee()!=Payee.payee && cmd.isValidee() && cmd.getMontantReste()>montant) {
			cmd.setMontantPaye(cmd.getMontantPaye()+montant);
			cmd.setMontantReste(cmd.getMontantReste()-montant);
			client.setCredit(client.getCredit()+montant);
			if(client.getCredit()>1000) client.setStatus(Status.auth);
			if(cmd.getMontantReste()==0)cmd.setPayee(Payee.payee);
			else {
				cmd.setPayee(Payee.partiellementPayee);
				}
		}
		cmdr.save(cmd);
		cr.save(client);
	}

	@Override
	public List<Commande> commandesParClient(Long idClient) {
		Client client=cs.clientParId(idClient);
		return client.getCommandes();
	}

}
