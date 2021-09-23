package domaine;


import static util.Util.*;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import exceptions.QuantiteNonAutoriseeException;

public class Prix implements Cloneable  {

	private final Promo typePromo;
	private final double valeurPromo;
	private SortedMap<Integer,Double> prixParQuantite = new TreeMap<Integer, Double>();

	
	public Prix(){
		typePromo = null;
		valeurPromo = 0;
	}
	
	public Prix(Promo promo, double valeurPromo){
		checkObject(promo);
		checkPositiveOrZero(valeurPromo);
		this.typePromo = promo;
		this.valeurPromo = valeurPromo;
	}
	

	public Promo getTypePromo() {
		return typePromo;
	}

	public double getValeurPromo() {
		return valeurPromo;
	}
	
	/**
	 * Cette m�thode permet de d�finir le prix unitaire correspondant � une quantit� minimale. 
	 * S'il existe d�j� un prix correspondant � la quantit�, il sera remplac�.
	 * @param quantiteMin
	 * @param valeur le prix unitaire � partir de cette quantit�
	 * @throws IllegalArgumentException en cas de quantit� n�gative ou nulle ou en cas de valeur n�gative ou nulle
	 */
	public void definirPrix(int quantiteMin, double valeur) {
		checkPositive(valeur);
		checkPositive(quantiteMin);
		prixParQuantite.put(quantiteMin, valeur);
	}

	
	/**
	 * Cette m�thode renvoie le prix � appliquer selon la quantit� achet�e
	 * @param la quantit� achet�e
	 * @return le prix unitaire pour cette quantit�
	 * @throws QuantiteNonAutoriseeException si la quantit� est inf�rieure � la quantit� minimale autoris�e
	 * @throws IllegalArgumentException en cas de quantit� n�gative ou nulle
	 */
	public double getPrix(int quantite) throws QuantiteNonAutoriseeException{
		checkPositive(quantite);
		double valeur=0;
		for(Entry<Integer, Double> entry : prixParQuantite.entrySet()) {
			if(entry.getKey() <= quantite) {
				valeur=entry.getValue();
			}
		}
		if(valeur == 0) {
			throw new QuantiteNonAutoriseeException();
		}
		return valeur;
	}

	
	@Override
	public String toString() {
		String detail = "";
		if (typePromo != null) detail += "Promo : " + typePromo + " - " + valeurPromo+"\n";
		//TODO Ajoutez les diff�rents prix en passant � la ligne entre chaque prix. Format attendu :
		for(Entry<Integer, Double> entry : prixParQuantite.entrySet()) {
			detail+=entry.getValue()+" euros à partir de "+entry.getKey()+" unités\n";
		}
		/*
		 * 99.9 euros � partir de 1 unit�s
		 * 89.9 euros � partir de 3 unit�s
		 * 59.9 euros � partir de 5 unit�s
		 */
		
		return detail;
	}

	@Override
	public Prix clone() throws CloneNotSupportedException {
		Prix p = (Prix)super.clone();
		p.prixParQuantite=(SortedMap<Integer, Double>) ((TreeMap<Integer, Double>) p.prixParQuantite).clone();
		return p;
	}
	
	
	

}
