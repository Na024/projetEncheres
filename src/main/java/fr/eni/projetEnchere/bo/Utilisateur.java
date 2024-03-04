package fr.eni.projetEnchere.bo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Utilisateur implements Serializable{
    private static final long serialVersionUID = 1L;

    //Attributs
    private Integer noUtilisateur;
    
    @NotBlank
    @Size(min = 2, max = 30)
    private String pseudo;
    
    @NotBlank
    @Size(min = 2, max = 30)
    private String nom;
    
    @NotBlank
    @Size(min = 2, max = 30)
    private String prenom;
    
    @NotBlank
    @Pattern(regexp = "([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)")
    private String email;
    
    @NotBlank
    @Pattern(regexp = "[0]{1}[1-9]{1}[0-9]{8}")
    private String telephone;
    
    @NotBlank
    @Size(min =2, max = 30)
    private String rue;
    
    @NotBlank
    @Size (min = 5, max = 5)
    @Pattern (regexp = "((0[1-9])|([1-8][0-9])|(9[0-8])|(2A)|(2B)) *([0-9]{3})?")
    private String codePostal;
    
    @NotBlank
    @Size(min = 2, max = 30)
    private String ville;
    
    @NotBlank
    @Size(min = 8, max = 16)
    @Pattern (regexp = "(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}")
    private String motDePasse;
    
    @NotNull
    @Min (1)
    @Max (999)
    private Integer credit;
    
    private boolean administrateur;
    private List<ArticleVendu>achats;
    private List<ArticleVendu>ventes;
    private List<Enchere>enchères;
    
    //Constructeurs
    public Utilisateur() {
    }
    
    
    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal,
            String ville, String motDePasse, Integer credit, boolean administrateur, List<ArticleVendu> achats,
            List<ArticleVendu> ventes, List<Enchere> enchères) {
        super();
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.administrateur = administrateur;
        this.achats = achats;
        this.ventes = ventes;
        this.enchères = enchères;
    }


    public Utilisateur(Integer noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
            String rue, String codePostal, String ville, String motDePasse, Integer credit, boolean administrateur,
            List<ArticleVendu> achats, List<ArticleVendu> ventes, List<Enchere> enchères) {
        super();
        this.noUtilisateur = noUtilisateur;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.administrateur = administrateur;
        this.achats = achats;
        this.ventes = ventes;
        this.enchères = enchères;
    }
    
    
    //Méthodes
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Utilisateur [noUtilisateur=");
        builder.append(noUtilisateur);
        builder.append(", pseudo=");
        builder.append(pseudo);
        builder.append(", nom=");
        builder.append(nom);
        builder.append(", prenom=");
        builder.append(prenom);
        builder.append(", email=");
        builder.append(email);
        builder.append(", telephone=");
        builder.append(telephone);
        builder.append(", rue=");
        builder.append(rue);
        builder.append(", codePostal=");
        builder.append(codePostal);
        builder.append(", ville=");
        builder.append(ville);
        builder.append(", motDePasse=");
        builder.append(motDePasse);
        builder.append(", credit=");
        builder.append(credit);
        builder.append(", administrateur=");
        builder.append(administrateur);
        builder.append(", achats=");
        builder.append(achats);
        builder.append(", ventes=");
        builder.append(ventes);
        builder.append(", enchères=");
        builder.append(enchères);
        builder.append("]");
        return builder.toString();
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(noUtilisateur);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Utilisateur other = (Utilisateur) obj;
        return Objects.equals(noUtilisateur, other.noUtilisateur);
    }
    
    
    
    //Gettes et Setters
    public Integer getNoUtilisateur() {
        return noUtilisateur;
    }
    public void setNoUtilisateur(Integer noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }
    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getRue() {
        return rue;
    }
    public void setRue(String rue) {
        this.rue = rue;
    }
    public String getCodePostal() {
        return codePostal;
    }
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }
    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public Integer getCredit() {
        return credit;
    }
    public void setCredit(Integer credit) {
        this.credit = credit;
    }
    public boolean isAdministrateur() {
        return administrateur;
    }
    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }
    public List<ArticleVendu> getAchats() {
        return achats;
    }
    public void setAchats(List<ArticleVendu> achats) {
        this.achats = achats;
    }
    public List<ArticleVendu> getVentes() {
        return ventes;
    }
    public void setVentes(List<ArticleVendu> ventes) {
        this.ventes = ventes;
    }
    public List<Enchere> getEnchères() {
        return enchères;
    }
    public void setEnchères(List<Enchere> enchères) {
        this.enchères = enchères;
    }

    
    
}