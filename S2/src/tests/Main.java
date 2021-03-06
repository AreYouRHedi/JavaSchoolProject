package tests;

import java.util.Iterator;

import cuisine.Ingredient;
import cuisine.IngredientQuantifie;
import cuisine.Instruction;
import cuisine.Plat;
import cuisine.Plat.Cout;
import cuisine.Plat.Difficulte;
import cuisine.Unite;

public class Main {

	/**
	 * @param args
	 * @throws CloneNotSupportedException 
	 */
	public static void main(String[] args) throws CloneNotSupportedException {

		Plat plat = null;
		plat = new Plat("Waterzooi", 4, Difficulte.XX, Cout.$$$);
		Instruction instruction = new Instruction("Couper les légumes", 15);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Faire revenir les légumes", 5);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Ajouter le poulet", 0);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Laisser mijoter jusqu'à cuisson du poulet", 67);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Ajouter la crême et servir", 0);
		plat.ajouterInstruction(instruction);
		Ingredient ing = new Ingredient("Blanc de poulet");
		plat.ajouterIngredient(ing, Unite.GRAMME, 600);
		ing = new Ingredient("Céleri");
		plat.ajouterIngredient(ing, Unite.GRAMME, 200);
		ing = new Ingredient("Carottes");
		plat.ajouterIngredient(ing, 2);
		ing = new Ingredient("Sel");
		plat.ajouterIngredient(ing, Unite.PINCEE, 1);
		ing = new Ingredient("Crême fraiche");
		plat.ajouterIngredient(ing, Unite.CENTILITRE, 10);
		System.out.println(plat+"\n********************************************************");
		System.out.println("Ingrédients triés : ");
		Iterator<IngredientQuantifie> ingredients = plat.ingredients();
		while(ingredients.hasNext()){
			System.out.println(ingredients.next());
		}
	}

}
