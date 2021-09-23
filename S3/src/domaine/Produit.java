package domaine;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Map.Entry;

import javax.sound.sampled.ReverbType;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

import static util.Util.*;
import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;

public class Produit implements Cloneable  {

	private final String nom;
	private final String rayon;
	private final String marque;
	
	//TODO initialiser histriquePrix de sorte que le tri se fasse par ordre décroissant de date
	private SortedMap<LocalDate,Prix> historiquePrix= new TreeMap<LocalDate, Prix>(Comparator.reverseOrder());
	
	public Produit(String nom, String marque, String rayon) {
		checkString(nom);
		checkString(marque);
		checkString(rayon);
		this.nom = nom;
		this.marque = marque;
		this.rayon = rayon;
	}
	
	public String getMarque() {
		return marque;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getRayon() {
		return rayon;
	}
	
	/**
	 * ajoute un prix dans l'historique si la date n'est pas déjà présente
	 * @param date la date à partir duquel les prix s'appliquent
	 * @param prix
	 * @throws DateDejaPresenteException si la date est déjà présente dans l'historique
	 * @throws CloneNotSupportedException 
	 * @throws IllegalArgumentException en cas de paramètre invalide
	 */
	public void ajouterPrix(LocalDate date, Prix prix) throws DateDejaPresenteException, CloneNotSupportedException{
		checkObject(prix);
		checkObject(date);
		if(historiquePrix.containsKey(date)) {
			throw new DateDejaPresenteException();
		}
		historiquePrix.put(date, prix.clone());
	}
	
	/**
	 * Renvoie le prix appliqué à une date donnée 
	 * @param date la date pour laquelle il faut retrouver le prix
	 * @return le prix
	 * @throws PrixNonDisponibleException s'il n'y a pas de prix disponible pour cette date
	 * @throws CloneNotSupportedException 
	 * @throws IllegalArgumentException en cas de paramètre invalide
	 */
	public Prix getPrix(LocalDate date) throws PrixNonDisponibleException, CloneNotSupportedException{
		checkObject(date);
		for(Entry<LocalDate,Prix> entry : historiquePrix.entrySet()) {
			if(entry.getKey().isEqual(date) || entry.getKey().isBefore(date)) {
				return entry.getValue().clone();
			}
		}
		throw new PrixNonDisponibleException();
	}

	@Override
	public String toString() {
		String detail = "Nom : " + nom + "\nMarque : " + marque + "\nRayon : " + rayon + "\nHistorique des prix :";
		//TODO Initialisez le formater de sorte que les dates s'affichent sous la forme jour mois année (Ex : 3 janvier 2019)
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd MMMM yyyy");
		for (Entry<LocalDate, Prix> entry : historiquePrix.entrySet()) {
			detail += "\n" + formater.format(entry.getKey()) + " :\n" + entry.getValue(); 
		}
		
		return detail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((marque == null) ? 0 : marque.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((rayon == null) ? 0 : rayon.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produit other = (Produit) obj;
		if (marque == null) {
			if (other.marque != null)
				return false;
		} else if (!marque.equals(other.marque))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (rayon == null) {
			if (other.rayon != null)
				return false;
		} else if (!rayon.equals(other.rayon))
			return false;
		return true;
	}

	@Override
	public Produit clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		 Produit p =(Produit)super.clone();
		 p.historiquePrix=(SortedMap<LocalDate, Prix>) ((TreeMap<LocalDate, Prix>) this.historiquePrix).clone();
		return p;
	}
	
	//TODO Ajoutez les méthodes qui permettent de définir l'unicité sur base du nom, de la marque et du rayon
	


}
