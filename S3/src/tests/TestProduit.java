package tests;

import java.time.LocalDate;

import domaine.Prix;
import domaine.Produit;
import domaine.Promo;
import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;

public class TestProduit {

	public static void main(String[] args) throws CloneNotSupportedException {
		Produit jeanVert = new Produit("Jeans Vert", "One Green Elephant", "F4");
		Prix prix = new Prix();
		prix.definirPrix(1, 79.9);
		prix.definirPrix(3, 69.9);
		prix.definirPrix(5, 59.9);
		try {
			jeanVert.ajouterPrix( LocalDate.of(2018, 12, 3), prix);
		} catch (DateDejaPresenteException e) {
			e.printStackTrace();
		}
		prix = new Prix(Promo.DESTOCKAGE, 50);
		prix.definirPrix(1, 39.9);
		prix.definirPrix(3, 34.9);
		prix.definirPrix(5, 29.9);
		try {
			jeanVert.ajouterPrix(LocalDate.of(2019, 2, 2), prix);
		} catch (DateDejaPresenteException e1) {
			e1.printStackTrace();
		}
		prix = new Prix(Promo.SOLDE, 30);
		prix.definirPrix(1, 55.9);
		prix.definirPrix(3, 48.9);
		prix.definirPrix(5, 41.9);
		try {
			jeanVert.ajouterPrix(LocalDate.of(2019, 1, 3), prix);
		} catch (DateDejaPresenteException e) {
			e.printStackTrace();
		}
		System.out.println(jeanVert);
		
		System.out.println("Le prix du jeans vert le 2 janvier 2019 :\n");
		try {
			System.out.println(jeanVert.getPrix(LocalDate.of(2019, 1, 2)));
		} catch (PrixNonDisponibleException e) {
			e.printStackTrace();
		}
		
		System.out.println("Le prix du jeans vert le 3 janvier 2019 :\n");
		try {
			System.out.println(jeanVert.getPrix(LocalDate.of(2019, 1, 3)));
		} catch (PrixNonDisponibleException e) {
			e.printStackTrace();
		}
		
		System.out.println("Le prix du jeans vert le 4 janvier 2019 :\n");
		try {
			System.out.println(jeanVert.getPrix(LocalDate.of(2019, 1, 4)));
		} catch (PrixNonDisponibleException e) {
			e.printStackTrace();
		}
		
		System.out.println("Le prix du jeans vert le 2 févrrier 2019 :\n");
		try {
			System.out.println(jeanVert.getPrix(LocalDate.of(2019, 2, 2)));
		} catch (PrixNonDisponibleException e) {
			e.printStackTrace();
		}
		
		System.out.println("Le prix du jeans vert le 5 févrrier 2019 :\n");
		try {
			System.out.println(jeanVert.getPrix(LocalDate.of(2019, 2, 5)));
		} catch (PrixNonDisponibleException e) {
			e.printStackTrace();
		}
				
		System.out.println("Le prix du jeans vert le 2 décembre 2018 :\n");
		try {
			System.out.println(jeanVert.getPrix(LocalDate.of(2018, 12, 2)));
		} catch (PrixNonDisponibleException e) {
			e.printStackTrace();
		}

		prix = new Prix(Promo.DESTOCKAGE, 70);
		prix.definirPrix(1, 23.9);
		prix.definirPrix(3, 20.9);
		prix.definirPrix(5, 17.9);
		try {
			jeanVert.ajouterPrix(LocalDate.of(2019, 2, 2), prix);
		} catch (DateDejaPresenteException e1) {
			e1.printStackTrace();
		}

	}

}
