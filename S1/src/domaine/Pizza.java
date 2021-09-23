package domaine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Pizza implements Iterable<Ingredient> {
	private String description;
	private String titre;
	public final static double PRIX_BASE=4.0;
	private List<Ingredient> ingredients;
	
	public Pizza(String description, String titre) {
		this.description=description;
		this.titre=titre;
		this.ingredients= new ArrayList<Ingredient>();
	}

	public String getDescription() {
		return description;
	}

	public String getTitre() {
		return titre;
	}
	public Iterator<Ingredient> iterator() {
		return ingredients.iterator();
	}
	public boolean ajouterIngredient(Ingredient ingredient) {
		if(ingredients.contains(ingredient)) {
			return false;
		}
		ingredients.add(ingredient);
		return true;
	}
	public boolean supprimerIngredient(Ingredient ingredient) {
		if(ingredients.contains(ingredient)) {
			ingredients.remove(ingredient);
			return true;
		}
		return false;
		
	}
	public double calculerPrix() {
		double prix=PRIX_BASE;
		for(Ingredient ingredient : ingredients) {
			prix+=ingredient.getPrix();
		}
		return prix;
	}

	@Override
	public String toString() {
		return "Pizza [description=" + description + ", titre=" + titre + "]";
	}
	
	
	
}
