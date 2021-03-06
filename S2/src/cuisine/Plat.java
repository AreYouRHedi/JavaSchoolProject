package cuisine;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static util.Util.*;

public class Plat {
	public enum Difficulte  {
		X("*"), 
		XX("**"), 
		XXX("***"), 
		XXXX("****"),
		XXXXX("*****");
		private String etoile;
		private Difficulte(String etoile) {
			this.etoile=etoile;
		}
		public String toString() {
			return etoile;
		}
	}
	public enum Cout  {
		$("€"), 
		$$("€€"), 
		$$$("€€€"), 
		$$$$("€€€€"),
		$$$$$("€€€€€");
		private String euro;
		private Cout(String euro) {
			this.euro=euro;
		}
		public String toString() {
			return euro;
		}
	}
	private final String nom;
	private int nbPersonnes;
	private Difficulte niveauDeDifficulte;
	private Cout cout;
	private Duration dureeEnMinutes;
	private List<Instruction> instructions = new ArrayList<Instruction>();
	private Set<IngredientQuantifie> ingredients= new HashSet<IngredientQuantifie>();
	public Plat(String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout) {
		super();
		checkString(nom);
		checkPositive(nbPersonnes);
		checkObject(niveauDeDifficulte);
		checkObject(cout);
		this.nom = nom;
		this.nbPersonnes = nbPersonnes;
		this.niveauDeDifficulte = niveauDeDifficulte;
		this.cout = cout;
		this.dureeEnMinutes=dureeEnMinutes.ofMinutes(0);
	}
	public String getNom() {
		return nom;
	}
	public int getNbPersonnes() {
		return nbPersonnes;
	}
	public Difficulte getNiveauDeDifficulte() {
		return niveauDeDifficulte;
	}
	public Cout getCout() {
		return cout;
	}
	public Duration getDureeEnMinutes() {
		return dureeEnMinutes;
	}
	public void insererInstruction(int indice, Instruction instruction) throws CloneNotSupportedException {
		checkPositiveOrZero(indice);
		checkObject(instruction);
		dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
		instructions.add(indice, instruction.clone());
	}
	
	 public void ajouterInstruction (Instruction instruction) throws CloneNotSupportedException {
		 checkObject(instruction);
		 dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
		 instructions.add(instruction.clone());
	 }
	
	public Instruction remplacerInstruction (int indice, Instruction instruction) throws CloneNotSupportedException {
		checkPositiveOrZero(indice);
		checkObject(instruction);
		dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
		Instruction instructionRemplacee = instructions.set(indice, instruction.clone());
		dureeEnMinutes = dureeEnMinutes.minus(instructionRemplacee.getDureeEnMinutes());
		return instructionRemplacee;
	}
	
	 public Instruction supprimerInstruction (int indice) {
		 checkPositiveOrZero(indice);
		 Instruction instructionSup = instructions.remove(indice);
		 dureeEnMinutes = dureeEnMinutes.minus(instructionSup.getDureeEnMinutes());
		 return instructionSup;
	 }
	 public List<String> getRecette() {
		 List<String> recette = new ArrayList<String>();
		 for(Instruction instruction : instructions) {
			 recette.add(instruction.toString());
		 }
		 return recette;
	 }
	// renvoie les instructions de la recette.
	public Iterator<Instruction> instructions() {
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Instruction> {
		private Iterator<Instruction> it = instructions.iterator();

		@Override
		public boolean hasNext() {
			
			return it.hasNext();
		}

		@Override
		public Instruction next() {
			// TODO Auto-generated method stub
			try {
				return it.next().clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				throw new InternalError();
			}
		}
	}
	public boolean ajouterIngredient(Ingredient ingredient, Unite unite, int quantite) throws CloneNotSupportedException {
		checkObject(ingredient);
		checkObject(unite);
		checkPositive(quantite);
		 return ingredients.add(new IngredientQuantifie(ingredient, quantite, unite));
	}
	public boolean ajouterIngredient(Ingredient ingredient, int quantite) throws CloneNotSupportedException {
		return ajouterIngredient(ingredient, Unite.NEANT, quantite);
	}
	public boolean modifierIngredient(Ingredient ingredient, Unite unite, int quantite) {
		checkObject(ingredient);
		checkObject(unite);
		checkPositive(quantite);
		IngredientQuantifie ingredientQuantifie= new IngredientQuantifie(ingredient, quantite, unite);
		for(IngredientQuantifie ing : ingredients ) {
			if(ing.getIngredient().equals(ingredientQuantifie.getIngredient())) {
				ingredients.remove(ing);
				ingredients.add(ingredientQuantifie);
				return true;
			}
		}
		return false;
	}
	public boolean supprimerIngredient(Ingredient ingredient) {
		checkObject(ingredient);
		for(IngredientQuantifie ing : ingredients ) {
			if(ing.getIngredient().equals(ingredient)) {
				ingredients.remove(ing);
				return true;
			}
		}
		return false;
	}
	// renvoie false si l’ingredient n’est pas présent.
	public Iterator<IngredientQuantifie> ingredients() {
		Set<IngredientQuantifie> coll = new TreeSet<IngredientQuantifie>(Comparator.comparing(a->a.getIngredient().getNom()));
		coll.addAll(ingredients);
		return coll.iterator();
	}
	// fournit un iterateur d’ingrédient quantifié trié par nom d’ingrédient.
	// Utilisez une expression lambda pour passer un Comparator lors de la création
	// de votre collection temporaire afin de trier selon le nom de l’ingrédient :
	public IngredientQuantifie getQuantite(Ingredient ingredient) throws CloneNotSupportedException {
		checkObject(ingredient);
		for(IngredientQuantifie ing : ingredients ) {
			if(ing.getIngredient().equals(ingredient)) {
				return ing.clone();
			}
		}
		return null;
	}
	// renvoie null si l’ingredient n’est pas présent. 
	@Override
	public String toString() {
		String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutes()%60);
		String res = this.nom + "\n\n";
		res += "Pour " + this.nbPersonnes + " personnes\n";
		res += "Difficulté : " + this.niveauDeDifficulte + "\n";
		res += "Coût : " + this.cout + "\n";
		res += "Durée : " + hms + " \n\n";
		res += "Ingrédients :\n";
		for (IngredientQuantifie ing : this.ingredients) {
			res += ing + "\n";
		}
		int i = 1;
		res += "\n";
		for (Instruction instruction : this.instructions) {
			res += i++ + ". " + instruction + "\n";
		}
		return res;
	}
	
	
	
}
