package com.riad.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.riad.app.entities.clients.Client;
import com.riad.app.entities.clients.TypeClient;
import com.riad.app.entities.stock.Produit;
import com.riad.app.services.clentsCommercial.ClientService;
import com.riad.app.services.stock.FPService;
import com.riad.app.services.stock.ProduitService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class WebController {
	
	
	private final StockController sc;
	private final FPService fps;
	private final ClientsController cc;
	private final ClientService cs;
	@GetMapping(path="/toto")
	public String produits(Model model) {
		model.addAttribute("produits",sc.findAllProduct());
		return "/produits";
	}
	@GetMapping(path="/addproduit")
	public String ajouterProduit(Model model) {
		double prix=0;
	
		model.addAttribute("familles",fps.allFams());
		model.addAttribute("code","");
		model.addAttribute("nomProduit","");
		model.addAttribute("prix",new Double(0));
		model.addAttribute("idFamile",new Long(0));
		return "/addProduit";
	}
	
	@PostMapping(path="/submitAddProduit")
	public String ajouterProduitSubmit(@ModelAttribute Produit produit) {
		sc.ajouterProduit(produit.getCode(),produit.getNomProduit(),produit.getPrix(),produit.getFamille().getId());
		return "redirect : /toto";
	}
	
	@GetMapping("/formClient")
	public String formCliet(Model model) {
		
		return "addProduit";
	}
@PostMapping("/webclients")
	public String ajouterClient(@ModelAttribute String nom,@ModelAttribute String prenom,@ModelAttribute String email,@ModelAttribute String cin,@ModelAttribute TypeClient typeClient,@ModelAttribute double plafond,@ModelAttribute String adresse) {
		cc.ajouterClient(nom, prenom,  cin, email,adresse, plafond, typeClient);
		return "addProduit";
	}
@GetMapping("webclients")
public String listerClients(Model model) {
	model.addAttribute("clients",cs.allClients());
	return "listClients";
}

@GetMapping("/clients/delete/{id}")
public String supprimerClient(@PathVariable Long id ,Model model) {
	cs.supprimerClient(id);
	model.addAttribute("clients",cs.allClients());
	return "/listClients";
}
	
	
	


}
