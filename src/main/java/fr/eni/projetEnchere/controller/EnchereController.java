package fr.eni.projetEnchere.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.projetEnchere.bll.EnchereService;
import fr.eni.projetEnchere.bo.ArticleVendu;
import fr.eni.projetEnchere.bo.Categorie;
import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.dal.EnchereRepository;

@Controller
public class EnchereController {

	
	
	private EnchereService enchereService;
	private EnchereRepository categorieRepository;
	private EnchereRepository enchereRepository;

	
	
        public EnchereController(EnchereService enchereService, EnchereRepository categorieRepository,
			EnchereRepository enchereRepository) {
		super();
		this.enchereService = enchereService;
		this.categorieRepository = categorieRepository;
		this.enchereRepository = enchereRepository;
	}



		@GetMapping("/encheres")
        public String listeEncheres(@RequestParam(name= "motCle",required = false) String motCle,@RequestParam(name= "noCategorie",required = false)  Integer noCategorie, Model model) {
			 List<ArticleVendu> articleVendus = new ArrayList<>();
			if (motCle != null && noCategorie != null) {
				
			  articleVendus = enchereService.findArticleByMotCleAndCategorie(motCle, noCategorie);
			  model.addAttribute("articleVendus", articleVendus);
		}
			
        	model.addAttribute("motCle", motCle);
        	model.addAttribute("noCategorie", noCategorie);
        	
			List<Categorie> categories = this.enchereService.getAllCategories();
			model.addAttribute("categories", categories);
			
        	return "encheres";

        }
		

		@GetMapping("/detailVente")
		public String afficherDetailVente (){
			return "detailVente";
			
		}
		
		
		
	}



