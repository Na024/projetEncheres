package fr.eni.projetEnchere.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import fr.eni.projetEnchere.bll.EnchereService;
import fr.eni.projetEnchere.bo.Categorie;

public class StringToCategorie implements Converter<String, Categorie>{
	

	private EnchereService enchereService;
	
	public StringToCategorie(EnchereService enchereService) {
		this.enchereService = enchereService;
	}
	
	@Override
	public Categorie convert(String source) {
		System.out.println("StringToCategorie.convert()");
		int noCategorie = Integer.parseInt(source);
		System.out.println(noCategorie);
		Categorie categorie = enchereService.consulterCategorieParId(noCategorie);
		System.err.println(categorie);
		return categorie;
	}
	
	
	
}
