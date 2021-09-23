package domaine;

public class Ingredient {
	private String nom;
	private double prix;
	public Ingredient(String nom, double prix) {
		super();
		this.nom = nom;
		this.prix = prix;
	}
	public String getNom() {
		return nom;
	}
	public double getPrix() {
		return prix;
	}
	
}
