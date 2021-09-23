package hack6;

public class DomaineA {
	private String nom;
	private int montant;

	public String getNom() {
		return nom;
	}

	public int getMontant() {
		return montant;
	}

	public DomaineB[] getRelation() {
		return relation;
	}

	public boolean getTest() {
		throw new RuntimeException("BOUM");
	}

	private DomaineB[] relation = new DomaineB[10];

	public DomaineA(String nom, int montant) {
		this.nom = nom;
		this.montant = montant;
		for (int i = 0; i < relation.length; i++) {
			relation[i] = new DomaineB();
		}
	}

}
