package cuisine;
import static util.Util.*;
public class IngredientQuantifie implements Cloneable {
	private Ingredient ingredient;
	private int quantite;
	private Unite unite;
	public IngredientQuantifie(Ingredient ingredient, int quantite, Unite unite) {
		super();
		checkObject(ingredient);
		checkObject(unite);
		checkPositive(quantite);
		this.ingredient = ingredient;
		this.quantite = quantite;
		this.unite = unite;
	}
	public Ingredient getIngredient() {
		return ingredient;
	}
	public int getQuantite() {
		return quantite;
	}
	public Unite getUnite() {
		return unite;
	}
	public void setIngredient(Ingredient ingredient) {
		checkObject(ingredient);
		this.ingredient = ingredient;
	}
	public void setQuantite(int quantite) {
		checkPositive(quantite);
		this.quantite = quantite;
	}
	public void setUnite(Unite unite) {
		checkObject(unite);
		this.unite = unite;
	}
	@Override
	public String toString() {
		return quantite + " "+ unite + " "+ingredient.getNom();
	}
	@Override
	protected IngredientQuantifie clone() throws CloneNotSupportedException {
		return (IngredientQuantifie) super.clone();
	}
	
	
}
