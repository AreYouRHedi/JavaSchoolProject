package tests;

import java.time.LocalDate;

import domaine.Prix;
import domaine.Produit;
import domaine.Promo;
import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;
import exceptions.ProduitNonPresentException;
import usecase.ListeProduits;

public class TestListeProduits {

	/**
	 * @param args
	 * @throws CloneNotSupportedException 
	 */
	public static void main(String[] args) throws CloneNotSupportedException {
		ListeProduits listeProduits = new ListeProduits();
		Produit jeansVert = new Produit("Jeans Vert", "One Green Elephant", "F4");
		Produit jeansRose = new Produit("Jeans Rose", "One Green Elephant", "Y8");
		listeProduits.ajouterProduit(jeansVert);
		listeProduits.ajouterProduit(jeansRose);
		LocalDate date = null;
		try {
			Produit jeansVertC = new Produit("Jeans Vert", "One Green Elephant", "F4");
			
			Prix prix = new Prix();
			prix.definirPrix(1, 79.9);
			prix.definirPrix(3, 69.9);
			prix.definirPrix(5, 59.9);
			listeProduits.ajouterPrix(jeansVertC, date =LocalDate.of(2018, 12, 3), prix);
			listeProduits.ajouterPrix(jeansRose,date, prix);
				
			prix = new Prix(Promo.SOLDE, 30);
			prix.definirPrix(1, 55.9);
			prix.definirPrix(3, 48.9);
			prix.definirPrix(5, 41.9);
			listeProduits.ajouterPrix(jeansVertC,date = LocalDate.of(2019, 1, 3), prix);
			listeProduits.ajouterPrix(jeansRose,date, prix);
			
			prix = new Prix(Promo.SOLDE, 50);
			prix.definirPrix(1, 39.9);
			prix.definirPrix(3, 34.9);
			prix.definirPrix(5, 29.9);
			listeProduits.ajouterPrix(jeansVertC,date = LocalDate.of(2019, 1, 15), prix);
			listeProduits.ajouterPrix(jeansRose,date, prix);
			
			prix = new Prix(Promo.DESTOCKAGE, 50);
			prix.definirPrix(1, 39.9);
			prix.definirPrix(3, 34.9);
			prix.definirPrix(5, 29.9);
			listeProduits.ajouterPrix(jeansRose,LocalDate.of(2019, 2, 2), prix);
			prix.definirPrix(9, 20);
			listeProduits.ajouterPrix(jeansRose,LocalDate.of(2019, 2, 3), prix);

			System.out.println(listeProduits);			
			
			System.out.println("Le prix du jeans rose le 3 janvier 2019 :\n"+listeProduits.trouverPrix(new Produit("Jeans Rose", "One Green Elephant", "Y8"),LocalDate.of(2019, 1, 3)));
			System.out.println("Le prix du jeans vert le 2 décembre 2018 :\n");
			System.out.println(listeProduits.trouverPrix(new Produit("Jeans Vert", "One Green Elephant", "F4"),LocalDate.of(2018, 12, 2)));
			

	
		} catch (DateDejaPresenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PrixNonDisponibleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProduitNonPresentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Prix prix = new Prix();
			prix.definirPrix(1, 99.9);
			prix.definirPrix(2, 89.9);
			prix.definirPrix(4, 79.9);
			listeProduits.ajouterPrix(jeansRose, LocalDate.of(2019,1,3), prix);
		} catch (DateDejaPresenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProduitNonPresentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		try {
			Prix prix = new Prix();
			prix.definirPrix(1, 99.9);
			prix.definirPrix(2, 89.9);
			prix.definirPrix(4, 79.9);
			listeProduits.ajouterPrix(new Produit("Jeans classique","One Green Elephant","F4"), LocalDate.of(2019,1,3), prix);
		} catch (DateDejaPresenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProduitNonPresentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {

			listeProduits.trouverPrix(new Produit("Jeans classique","One Green Elephant","F4"), LocalDate.of(2019,1,3));
		} catch (ProduitNonPresentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PrixNonDisponibleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
