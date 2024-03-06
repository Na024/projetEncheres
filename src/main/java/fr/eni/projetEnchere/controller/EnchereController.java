package fr.eni.projetEnchere.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.eni.projetEnchere.bll.EnchereService;
import fr.eni.projetEnchere.bo.Categorie;
import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.dal.CategorieRepository;

@Controller
public class EnchereController {

	/*
	 * private EnchereService enchereService; private CategorieRepository
	 * categorieRepository;
	 * 
	 * @Autowired public CategorieController(EnchereService enchereService) {
	 * this.categorieRepository = categorieRepository; }
	 */
	
	private EnchereService enchereService;
	private CategorieRepository categorieRepository;

	
	
        public EnchereController(EnchereService enchereService, CategorieRepository categorieRepository) {
		super();
		this.enchereService = enchereService;
		this.categorieRepository = categorieRepository;
	}



		@GetMapping("/encheres")
        public String listeEncheres(Model model) {
			
        	List<Categorie> categories = this.enchereService.getAllCategories();
        	System.out.println(categories);
        	model.addAttribute("categories", categories);
        	return "encheres";

        }
	
	
	
    
	/*
	 * 
	 * 
	 * //Get mapping pose problème
	 * 
	 * @GetMapping("/encheres") public String getAllCategories(Model model) {
	 * 
	 * List<Categorie> categories = this.enchereService.getAllCategories();
	 * System.out.println(categories); model.addAttribute("categories", categories);
	 * 
	 * return "encheres"; }
	 */
    
    
}
