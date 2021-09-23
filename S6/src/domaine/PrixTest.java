package domaine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import exceptions.QuantiteNonAutoriseeException;

class PrixTest {
	private Prix prixAucune;
	private Prix prixPub;
	private Prix prixSolde;
	@BeforeEach
	void setUp() throws Exception {
		this.prixAucune=new Prix();
		this.prixPub=new Prix(Promo.PUB, 10);
		this.prixSolde=new Prix(Promo.SOLDE, 5);
		prixAucune.definirPrix(1, 20);
		prixAucune.definirPrix(10, 10);
		prixPub.definirPrix(3, 15);
	}

	@Test
	void testConstructeur1() {
		assertThrows(IllegalArgumentException.class, ()->new Prix(null, 0));
	}
	@Test
	void testConstructeur2() {
		assertThrows(IllegalArgumentException.class, ()->new Prix(Promo.PUB,-1));
	}
	@Test
	void testGetter1() {
		assertEquals(0, prixAucune.getValeurPromo());
	}
	@Test
	void testGetter2() {
		assertEquals(10, prixPub.getValeurPromo());
	}
	@Test
	void testGetter3() {
		assertEquals(null, prixAucune.getTypePromo());
	}
	@Test
	void testGetter4() {
		assertEquals(Promo.PUB, prixPub.getTypePromo());
	}
	@Test
	void testDefinirPrix1() {
		assertAll(
				()->assertThrows(IllegalArgumentException.class, ()-> prixAucune.definirPrix(0, 1)),
				()->assertThrows(IllegalArgumentException.class, ()-> prixAucune.definirPrix(-1, 1))
				);
		
	}
	@Test
	void testDefinirPrix2() {
		assertAll(
				()->assertThrows(IllegalArgumentException.class, ()-> prixAucune.definirPrix(1, 0)),
				()->assertThrows(IllegalArgumentException.class, ()-> prixAucune.definirPrix(1, -1))
				);
	}
	@Test
	void testDefinirPrix3() throws QuantiteNonAutoriseeException {
		double v = prixAucune.getPrix(10);
		prixAucune.definirPrix(10, 6);
		assertNotEquals(v, prixAucune.getPrix(10));
	}
	@Test
	void testGetPrix1() {
		assertAll(
				()->assertThrows(IllegalArgumentException.class, ()->prixAucune.getPrix(0)),
				()->assertThrows(IllegalArgumentException.class, ()->prixAucune.getPrix(-1))
				);
	}
	@ParameterizedTest
	@ValueSource(ints= {5,9,10,15,20,25})
	void testGetPrix2(int quantite) throws QuantiteNonAutoriseeException {
		assertEquals(15,prixPub.getPrix(quantite));
	}
	@Test
	void testGetPrix3() {
		assertThrows(QuantiteNonAutoriseeException.class, ()->prixPub.getPrix(2));
	}
	@Test
	void testGetPrix4() {
		assertThrows(QuantiteNonAutoriseeException.class, ()->prixSolde.getPrix(1));
	}
	@Test
	void testClone1() {
		assertNotSame(prixAucune, prixAucune.clone());
	}
	@Test
	void testClone2() {
		assertAll(
				()->assertEquals(prixPub.getTypePromo(), prixPub.clone().getTypePromo()),
				()->assertEquals(prixPub.getValeurPromo(), prixPub.clone().getValeurPromo())
				);
		
	}
	
	

}
