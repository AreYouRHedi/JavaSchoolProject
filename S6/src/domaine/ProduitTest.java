package domaine;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProduitTest {
	private Produit produitSansPrix;
	private Produit produitAvecPrix;
	private Prix prixAucune;
	private Prix prixPub;
	private Prix prixSolde;
	@BeforeEach
	void setUp() throws Exception {
		this.produitAvecPrix=new Produit("nom1", "marque1", "rayon1");
		this.produitSansPrix=new Produit("nom2", "marque2", "rayon2");
		this.prixAucune=new Prix();
		this.prixPub=new Prix(Promo.PUB, 10);
		this.prixSolde=new Prix(Promo.SOLDE, 5);
		prixAucune.definirPrix(1, 20);
		prixAucune.definirPrix(10, 10);
		prixPub.definirPrix(3, 15);
		produitAvecPrix.ajouterPrix(LocalDate.now(), prixAucune);
		produitAvecPrix.ajouterPrix(LocalDate.now().minusMonths(1), prixPub);
		produitAvecPrix.ajouterPrix(LocalDate.now().minusMonths(2), prixSolde);
	}

	@Test
	void testConstructeur1() {
		assertAll(
				()->assertThrows(IllegalArgumentException.class, ()->new Produit(null, "1", "1")),
				()->assertThrows(IllegalArgumentException.class, ()->new Produit("1", null, "1")),
				()->assertThrows(IllegalArgumentException.class, ()->new Produit("1", "1", null))
				);
	}
	@Test
	void testConstructeur2() {
		assertAll(
				()->assertThrows(IllegalArgumentException.class, ()->new Produit("", "1", "1")),
				()->assertThrows(IllegalArgumentException.class, ()->new Produit("1", "", "1")),
				()->assertThrows(IllegalArgumentException.class, ()->new Produit("1", "1", ""))
				);
	}
	@Test
	void testGetters() {
		assertAll(
				()->assertEquals("nom1", produitAvecPrix.getNom()),
				()->assertEquals("marque1", produitAvecPrix.getMarque()),
				()->assertEquals("rayon1", produitAvecPrix.getRayon())
				);
	}

}
