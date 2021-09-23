package tests;

import java.time.Duration;
import java.util.Iterator;

import cuisine.Ingredient;
import cuisine.IngredientQuantifie;
import cuisine.Instruction;
import cuisine.Plat;
import cuisine.Plat.Cout;
import cuisine.Plat.Difficulte;
import cuisine.Unite;

public class MainEtrange {

	/**
	 * @param args
	 * @throws CloneNotSupportedException 
	 */
	public static void main(String[] args) throws CloneNotSupportedException {

		Plat plat = null;
		plat = new Plat("Waterzooi", 4, Difficulte.XX, Cout.$$$);
		// Mauvaise technique mais qui doit fonctionner ici vu les clones
		Instruction instruction = new Instruction("Couper les légumes", 15);
		plat.ajouterInstruction(instruction);
		instruction.setDescription("Faire revenir les légumes");
		instruction.setDureeEnMinutes(Duration.ofMinutes(5));
		plat.ajouterInstruction(instruction);
		instruction.setDescription("Ajouter le poulet");
		instruction.setDureeEnMinutes(Duration.ofMinutes(0));
		plat.ajouterInstruction(instruction);
		instruction.setDescription("Laisser mijoter jusqu'à cuisson du poulet");
		instruction.setDureeEnMinutes(Duration.ofMinutes(67));
		plat.ajouterInstruction(instruction);
		instruction.setDescription("Ajouter la crême et servir");
		instruction.setDureeEnMinutes(Duration.ofMinutes(0));
		plat.ajouterInstruction(instruction);
		instruction.setDescription("Danser la macarena");
		instruction.setDureeEnMinutes(Duration.ofMinutes(66));

		Ingredient ing = new Ingredient("Blanc de poulet");
		plat.ajouterIngredient(ing, Unite.KILOGRAMME, 400);
		plat.modifierIngredient(ing, Unite.GRAMME, 600);
		ing = new Ingredient("Céleri");
		plat.ajouterIngredient(ing, Unite.GRAMME, 200);
		ing = new Ingredient("Carottes");
		plat.ajouterIngredient(ing, 2);
		plat.getQuantite(ing).setUnite(Unite.PINCEE);
		ing = new Ingredient("Sel");
		plat.ajouterIngredient(ing, Unite.PINCEE, 1);
		ing = new Ingredient("Crême fraiche");
		plat.ajouterIngredient(ing, Unite.CENTILITRE, 10);

		IngredientQuantifie ingredientQuantifie = plat.getQuantite(ing);
		ingredientQuantifie.setQuantite(99);
		ingredientQuantifie.setUnite(Unite.CUILLER_A_SOUPE);
		ing = new Ingredient("Chocolat");
		plat.ajouterIngredient(ing, Unite.GRAMME, 200);
		plat.supprimerIngredient(ing);
		System.out.println(plat);
		
		System.out.println("Ingrédients triés : ");
		Iterator<IngredientQuantifie> ingredients = plat.ingredients();
		while(ingredients.hasNext()){
			System.out.println(ingredients.next());
		}

	}

}
