package usecase;

import static util.Util.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;
import exceptions.ProduitNonPresentException;
import domaine.Prix;
import domaine.Produit;

public class ListeProduits {

																
    //TODO ajouter un attribut pour garder les produits																
	private SortedSet<Produit> produits = new TreeSet<Produit>(new Comparator<Produit>() {
		@Override
		public int compare(Produit o1, Produit o2) {
			if(o1.getNom().equals(o2.getNom())) {
				if(o1.getMarque().equals(o2.getMarque())) {
					return o1.getRayon().compareTo(o2.getRayon());
				}
				return o1.getMarque().compareTo(o2.getMarque());
			}
			return o1.getNom().compareTo(o2.getNom());
		}
		
	});

	/**
	 * Cette m�thode renvoie vraie si le produit pass� en param�tre est pr�sent dans la liste
	 * @param p le produit recherch�
	 * @return true si le produit est dans la liste, false sinon
	 * @throws IllegalArgumentException en cas de param�tre invalide
	 */
	public boolean contient(Produit p) {
		checkObject(p);
		return produits.contains(p);
	}

	/**
	 * Cette m�thode ajoute le produit si celui-ci ne s'y trouve pas encore
	 * @param p le produit � ajouter
	 * @return true si le produit a pu �tre ajout�, false sinon
	 * @throws CloneNotSupportedException 
	 * @throws IllegalArgumentException en cas de param�tre invalide
	 */
	public boolean ajouterProduit(Produit p) throws CloneNotSupportedException {
		checkObject(p);
		//TODO compl�tez la m�thode
		return produits.add(p.clone());
	}

	/**
	 * Cette m�thode supprime le produit si celui-ci est pr�sent dans la liste
	 * @param p le produit � supprimer
	 * @return true si le produit a pu �tre supprim�, false sinon
	 * @throws IllegalArgumentException en cas de param�tre invalide
	 */
	public boolean supprimerProduit(Produit p) {
		checkObject(p);
		return produits.remove(p);
	}


	/**
	 * Cette m�thode trouve le produit correspondant aux param�tres s'il existe et le renvoie
	 * @param nom le nom du produit recherch�
	 * @param marque la marque du produit recherch�
	 * @param rayon le rayon du produit recherch�
	 * @return le produit s'il existe, null sinon
	 * @throws CloneNotSupportedException 
	 */
	public Produit trouverProduit(String nom, String marque, String rayon) throws CloneNotSupportedException {
		for(Produit p : produits) {
			if(p.getNom().equals(nom) && p.getMarque().equals(marque) && p.getRayon().equals(rayon)) {
				return p.clone();
			}
		}
		return null;
	}

	/**
	 * Cette m�thode renvoie un it�rateur permettant de parcourir les produits
	 * @return un it�rateur sur les produits
	 */
	
	public Iterator<Produit> produits() {
		
		return new SetIterator();
	}
	private class SetIterator implements Iterator<Produit> {
		private Iterator<Produit> it = produits.iterator();
		public boolean hasNext() {
			return it.hasNext();
		}
		public Produit next() {
			try {
				return it.next().clone();
			}catch(CloneNotSupportedException e) {
				throw new InternalError();
			}
		}
	}


	/**
	 * Cette m�thode ajoute un prix � appliquer � partir d'une certaine date au produit 
	 * @param prod le produit auquel s'applique le prix
	 * @param date la date � partir de laquelle s'applique le prix
	 * @param prix le prix � appliquer
	 * @throws ProduitNonPresentException si le produit n'est pas dans la liste
	 * @throws DateDejaPresenteException s'il y a d�j� un prix pour cette date pour ce produit
	 * @throws CloneNotSupportedException 
	 * @throws IllegalArgumentException en cas de param�tre invalide ou si le produit n'est pas pr�sent
	 */
	public void ajouterPrix(Produit prod, LocalDate date, Prix prix) throws DateDejaPresenteException, ProduitNonPresentException, CloneNotSupportedException {
		checkObject(prod);
		Produit p =trouverProduit(prod);
		if(p==null) {
			throw new ProduitNonPresentException();
		}
		p.ajouterPrix(date, prix.clone());
	}
	
	/**
	 * Cette m�thode retrouve et renvoie le prix appliqu� � un certain produit � une date donn�e. 
	 * @param prod le produit dont on cherche le prix
	 * @param date la date pour laquelle on veut conna�tre le prix
	 * @throws ProduitNonPresentException si le produit n'est pas dans la liste
	 * @throws PrixNonDisponibleException s'il n'y a de prix pour cette date pour ce produit
	 * @throws CloneNotSupportedException 
	 * @throws IllegalArgumentException en cas de param�tre invalide ou si le produit n'est pas pr�sent
	 */
	public Prix trouverPrix(Produit prod,LocalDate date) throws ProduitNonPresentException, PrixNonDisponibleException, CloneNotSupportedException {
		checkObject(prod);
		Produit p = trouverProduit(prod);
		if(p==null) {
			throw new ProduitNonPresentException();
		}
		return p.getPrix(date);
	}
	


	public String toString() {
		String detail="";
		//TODO remplacer ??? pour parcourir les produits
		for(Produit p : produits){
			detail += p + "\n----------------------------------------------------------------------\n";
		}
		return detail;
	}
	private Produit trouverProduit(Produit prod) {
		for(Produit p : produits) {
			if(p.equals(prod)) {
				return p;
			}
		}
		return null;
	}

}
