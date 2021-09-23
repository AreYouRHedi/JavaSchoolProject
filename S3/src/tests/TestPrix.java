package tests;

import domaine.Prix;
import domaine.Promo;
import exceptions.QuantiteNonAutoriseeException;

public class TestPrix {

	public static void main(String[] args) {
		Prix prix = new Prix();
		prix.definirPrix(1, 79.9);
		prix.definirPrix(3, 69.9);
		prix.definirPrix(5, 59.9);
		System.out.println(prix);
		for (int i = 1; i <=6 ; i ++) {
			try {
				System.out.println("Prix unitaire pour une quantité de " + i + " : " + prix.getPrix(i));
			} catch (QuantiteNonAutoriseeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		prix.definirPrix(3,74.9);
		prix = new Prix(Promo.SOLDE, 30);
		prix.definirPrix(2, 89.9);
		prix.definirPrix(6, 84.9);
		prix.definirPrix(6, 79.9);
		prix.definirPrix(10, 69.9);
		System.out.println("\n"+prix);
		
		try {
			System.out.println("Prix pour une quantité de 1 : " + prix.getPrix(1));
		} catch (QuantiteNonAutoriseeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
