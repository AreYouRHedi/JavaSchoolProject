package domaine;

import java.util.List;
public class PizzaComposee extends Pizza {
	private double prix;
	private final static int REMISE=15;
	
	public PizzaComposee(String description, String titre,List<Ingredient> ingredients) {
		super(description,titre);
		for(Ingredient ingredient : ingredients) {
			ajouterIngredient(ingredient);
		}
		prix=calculerPrix();
	}
	@Override
	public double calculerPrix() {
		double prix= super.calculerPrix();
		prix-=Math.floor(prix*REMISE/100);
		return prix;
	}
}
