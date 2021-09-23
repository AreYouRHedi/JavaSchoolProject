package domaine;

public class LigneCommande {
	private int quantite;
	private double prixUnitaire;
	private Pizza pizza;
	public LigneCommande(int quantite, Pizza pizza) {
		super();
		this.quantite = quantite;
		this.pizza = pizza;
		this.prixUnitaire=pizza.calculerPrix();
	}
	public double calculerLigneDeCommande() {
		return prixUnitaire*quantite;
	}
	public int getQuantite() {
		return quantite;
	}
	public double getPrixUnitaire() {
		return prixUnitaire;
	}
	public Pizza getPizza() {
		return pizza;
	}
	@Override
	public String toString() {
		return "LigneCommande [quantite=" + quantite + ", prixUnitaire=" + prixUnitaire + ", pizza=" + pizza.toString() + "]";
	}
	
	
}
