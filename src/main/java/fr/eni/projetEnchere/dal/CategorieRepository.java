package fr.eni.projetEnchere.dal;

import java.util.List;
import java.util.Optional;

import fr.eni.projetEnchere.bo.Categorie;

public interface CategorieRepository {
	List<Categorie> a();

	void consulterCategorie(int noCategorie);

	Optional<Categorie> consulterCategorieParId(int noCategorie);
}
