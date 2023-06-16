package com.riad.app;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riad.app.entities.stock.Depot;
import com.riad.app.entities.stock.FamilleProduit;
import com.riad.app.entities.stock.Produit;
import com.riad.app.entities.stock.ProduitDispo;
import com.riad.app.entities.stock.TempoStock;
import com.riad.app.repositories.stock.ProduitDispoRepository;
import com.riad.app.services.stock.DepotService;
import com.riad.app.services.stock.FPService;
import com.riad.app.services.stock.ProduitDispoService;
import com.riad.app.services.stock.ProduitService;
import com.riad.app.services.stock.RegionService;
import com.riad.app.services.stock.TempoStockService;

import lombok.RequiredArgsConstructor;
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/stock")
@EnableMethodSecurity
public class StockController {
	
	private final ProduitService ps;
	private final FPService fps;
	private final DepotService ds;
	private final ProduitDispoService pds;
	private final RegionService rs;
	private final TempoStockService tss;
	private final ProduitDispoRepository pdr;
	
	@GetMapping(path="/produits")
	public List<Produit> findAllProduct(){
		ProduitService produitService=ps;
		List<Produit> prods=produitService.findAllProds();
		return prods;
	}
	
	@GetMapping(path="/famillesProduit")
	public List<FamilleProduit> tousLesFamilles() {
		return fps.allFams();
	}
	@GetMapping(path="/depots")
	public List<Depot> depots(){
		return ds.allDepots();
	}
	@GetMapping(path="/depots{idDepot}/produits")
	public List<ProduitDispo> produitsDepot(@PathVariable Long idDepot){
		Depot depot=ds.depotParId(idDepot);
		return pds.produitsDepot(depot);        
	}
	@GetMapping(path="/produit{codeProduit}/dispo")
	public List<ProduitDispo> produitStock(@PathVariable String codeProduit){
		Produit produit = ps.produitParId(codeProduit);
		return pds.produitStock(produit);
	}
	@GetMapping(path="/produit{codeProduit}")
	public Produit produit(@PathVariable String codeProduit) {
	
		return ps.produitParId(codeProduit);
		
	}
	@PostMapping("/pds{id}")
	public ResponseEntity<ProduitDispo> ret(@PathVariable Long id ){
		ProduitDispo pd=pdr.findById(id).get();
		pd.setQuant(9);
		pdr.save(pd);
		return ResponseEntity.ok(pd);
	}
	@GetMapping(path = "/depot{idDepot}/produit{codeProduit}")
	public ProduitDispo produitDepot(@PathVariable String idDepot,@PathVariable String codeProduit){
		Long id=Long.parseLong(idDepot);
		Depot depot=ds.depotParId(id);
		Produit produit = ps.produitParId(codeProduit);
		return pds.produitDepot(produit, depot);
		
	}
	@GetMapping("/famillesProduit{idFamille}")
	public FamilleProduit famille(@PathVariable String idFamille) {
		Long id=Long.parseLong(idFamille);
		return fps.familleParId(id);
	}
	@PreAuthorize("hasAuthority('GC')")
	@PostMapping("/produits")
	public ResponseEntity<Produit> ajouterProduit(String code,String nomProduit,double prix,Long idFamille) {
		Produit produit=Produit.builder()
				.code(code)
				.nomProduit(nomProduit)
				.prix(prix)
				.famille(fps.familleParId(idFamille))
				.build();
		Produit produitEnr=ps.ajouterProduit(produit);
		return ResponseEntity.created(URI.create("/stock/produits/"+produitEnr.getCode())).body(produitEnr);
		
	}	
	@PreAuthorize("hasAuthority('GC')")
	@PostMapping("/familleProduit")
	public ResponseEntity<FamilleProduit> ajouterFP(String libille) {
		FamilleProduit fpEnr=fps.ajouterFP(libille);
		return ResponseEntity.created(URI.create("/stock/famillesProduit/"+fpEnr.getId())).body(fpEnr);
		}
	@PreAuthorize("hasAuthority('GC')")
	@PostMapping("/depots")
	public  ResponseEntity<Depot> ajouterDepot(String adresse,Long idRegion) {
		Depot depotEnr = ds.ajouterDepot(rs.regionParId(idRegion), adresse);
		return ResponseEntity.created(URI.create("/stock/depot"+depotEnr.getNumDepot())).body(depotEnr);
	}
	
	@PreAuthorize("hasAuthority('GD')")
	@PostMapping("/depot{numDepot}/produit{codeProduit}")
	public ResponseEntity<TempoStock> ajouterQuantiteProduit(@PathVariable Long numDepot,@PathVariable String codeProduit,int qte){ 
		Produit produit =ps.produitParId(codeProduit);
		Depot depot =ds.depotParId(numDepot);
		TempoStock ts=tss.create(depot, produit, qte);
		return ResponseEntity.created(URI.create("/stock/depot"+numDepot)).body(ts);
	}
	@PreAuthorize("hasAuthority('GC')")
	@PostMapping("/tempo{id}/valider")
	public ResponseEntity<Depot> validerQuantiteProduit( @PathVariable Long id){ 
		TempoStock ts=tss.tempoStockParId(id);
		pds.ajouterQteProduit(ts.getProduit(), ts.getDepot(), ts.getQte());
		return ResponseEntity.created(URI.create("/stock/depot"+ts.getDepot().getNumDepot())).body(ts.getDepot());
	}
	@GetMapping("/produits/search")
	public List<Produit> chercherProduit(@RequestParam(name="mc",defaultValue = "") String mc){
		return ps.chercherProduit(mc);
	}	
}
