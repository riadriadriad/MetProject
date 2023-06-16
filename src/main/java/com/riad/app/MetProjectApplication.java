package com.riad.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.riad.app.entities.clients.TypeClient;
import com.riad.app.entities.stock.Produit;
import com.riad.app.repositories.clientsCommerciaux.ClientRepository;
import com.riad.app.services.clentsCommercial.ClientService;
import com.riad.app.services.clentsCommercial.CommercialService;
import com.riad.app.services.clentsCommercial.GroupeClientService;
import com.riad.app.services.clentsCommercial.PortefeuilleService;
import com.riad.app.services.stock.DepotService;
import com.riad.app.services.stock.FPService;
import com.riad.app.services.stock.ProduitDispoService;
import com.riad.app.services.stock.ProduitService;
import com.riad.app.services.stock.RegionService;

import lombok.RequiredArgsConstructor;
@SpringBootApplication
@RequiredArgsConstructor
@EnableAutoConfiguration
public class MetProjectApplication {
	private final ClientRepository cr;

	public static void main(String[] args) {
		SpringApplication.run(MetProjectApplication.class, args);
	}
	CommandLineRunner start(ProduitService ps,RegionService rs,FPService fps,DepotService ds,ProduitDispoService pds,ClientService cs,CommercialService comms,GroupeClientService gcs,PortefeuilleService pfs) {
		
		rs.ajouterRegion("Tanger-Tétouan-Al Hoceïma");
		rs.ajouterRegion("L'Oriental");
		rs.ajouterRegion("Fès-Meknès");
		rs.ajouterRegion("Rabat-Salé-Kénitra");
		rs.ajouterRegion("Béni Mellal-Khénifra");
		rs.ajouterRegion("Casablanca-Settat");
		rs.ajouterRegion("Marrakech-Safi");
		rs.ajouterRegion("Drâa-Tafilalet");
		rs.ajouterRegion("Souss-Massa");
		rs.ajouterRegion("Guelmim-Oued Noun");
		rs.ajouterRegion("Laâyoune-Sakia El Hamra");
		rs.ajouterRegion("Dakhla-Oued Ed-Dahab");
		
		fps.ajouterFP("famille 1");
		fps.ajouterFP("famille 2");
		fps.ajouterFP("famille 3");
		fps.ajouterFP("famille 4");
		fps.ajouterFP("famille 5");
		fps.ajouterFP("famille 6");
		
		ps.ajouterProduit(Produit.builder().code("P1").nomProduit("produit1").prix(120).famille(fps.familleParId(1L)).build());
		ps.ajouterProduit(Produit.builder().code("P2").nomProduit("produit2").prix(102).famille(fps.familleParId(2L)).build());
		ps.ajouterProduit(Produit.builder().code("P3").nomProduit("produit3").prix(20).famille(fps.familleParId(3L)).build());
		ps.ajouterProduit(Produit.builder().code("P4").nomProduit("produit4").prix(10).famille(fps.familleParId(2L)).build());
		ps.ajouterProduit(Produit.builder().code("P5").nomProduit("produit5").prix(120).famille(fps.familleParId(1L)).build());
		ps.ajouterProduit(Produit.builder().code("P6").nomProduit("produit6").prix(1220).famille(fps.familleParId(4L)).build());
		ps.ajouterProduit(Produit.builder().code("P7").nomProduit("produit7").prix(1420).famille(fps.familleParId(4L)).build());
		ps.ajouterProduit(Produit.builder().code("P8").nomProduit("produit8").prix(2120).famille(fps.familleParId(4L)).build());
		ps.ajouterProduit(Produit.builder().code("P9").nomProduit("produit9").prix(120).famille(fps.familleParId(5L)).build());
		ps.ajouterProduit(Produit.builder().code("P10").nomProduit("produit10").prix(120).famille(fps.familleParId(5L)).build());
		ps.ajouterProduit(Produit.builder().code("P11").nomProduit("produit11").prix(120).famille(fps.familleParId(1L)).build());
		
		ds.ajouterDepot(rs.regionParId(1L), "depot tanger");
		ds.ajouterDepot(rs.regionParId(2L), "depot 	Oriental");
		ds.ajouterDepot(rs.regionParId(3L), "depot Fes");
		ds.ajouterDepot(rs.regionParId(4L), "depot Sale");
		ds.ajouterDepot(rs.regionParId(5L), "depot Beni Mellal");
		ds.ajouterDepot(rs.regionParId(6L), "depot Settat");
		ds.ajouterDepot(rs.regionParId(7L), "depot Safi");
		ds.ajouterDepot(rs.regionParId(8L), "depot Tafilalet");
		ds.ajouterDepot(rs.regionParId(9L), "depot Souss");
		
		pds.ajouterProduitDispo(ps.produitParId("P1"),ds.depotParId(1L),250);
		pds.ajouterProduitDispo(ps.produitParId("P2"),ds.depotParId(1L),1766);
		pds.ajouterProduitDispo(ps.produitParId("P3"),ds.depotParId(1L),900);
		pds.ajouterProduitDispo(ps.produitParId("P1"),ds.depotParId(2L),890);
		pds.ajouterProduitDispo(ps.produitParId("P2"),ds.depotParId(2L),298);
		pds.ajouterProduitDispo(ps.produitParId("P3"),ds.depotParId(2L),250);
		pds.ajouterProduitDispo(ps.produitParId("P1"),ds.depotParId(3L),250);
		pds.ajouterProduitDispo(ps.produitParId("P2"),ds.depotParId(3L),250);
		pds.ajouterProduitDispo(ps.produitParId("P3"),ds.depotParId(3L),250);
		pds.ajouterProduitDispo(ps.produitParId("P4"),ds.depotParId(3L),250);
		pds.ajouterProduitDispo(ps.produitParId("P5"),ds.depotParId(3L),250);
		

		
		comms.ajouterCommercial("jamal", "jamal", "jamal@gmail.com", "jamal", "1234");
		comms.ajouterCommercial("khalid", "abdp", "abdo@gmail.com", "abdo", "1234");
		comms.ajouterCommercial("jamal", "hamid", "hamid@gmail.com", "hamid", "1234");
		comms.ajouterCommercial("Omari", "Sanaa", "Omari@gmail.com", "Sanaa", "1234");
		comms.ajouterCommercial("Jalloul", "Hafsa", "Jalloul@gmail.com", "Hafsa", "1234");

		gcs.ajouterGroupe("Groupe sus", 190000);
		gcs.ajouterGroupe("HANAGROUP", 1970000);
		gcs.ajouterGroupe("METAMAGROUP", 1765000);
		
		pfs.ajouterPF("pfCasa", rs.regionParId(6L));
		pfs.ajouterPF("pfTanger", rs.regionParId(1L));
		pfs.ajouterPF("pfFes", rs.regionParId(4L));
		pfs.ajouterPF("pfMeknes", rs.regionParId(3L));
		pfs.ajouterPF("pf", rs.regionParId(2L));
		
		return args->{
			
			
		};
	}
	@Bean
	public PasswordEncoder pe() {
		return new BCryptPasswordEncoder();
	}
}
