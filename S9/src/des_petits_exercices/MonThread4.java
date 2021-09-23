package des_petits_exercices;

class Compte {
	private int solde = 0;
	
	public synchronized void ajouter(int somme) {
		solde += somme;
		System.out.println(" ajoute " + somme);
	}
	public synchronized void retirer(int somme) {
		solde -= somme;
		System.out.println(" retire " + somme);
	}

	public synchronized int getSolde() {
		return solde;
	}
}

class Operation extends Thread {
	private Compte compte;

	public Operation(String nom, Compte compte) {
		super(nom);
		this.compte = compte;
	}

	public void run() {
		while (true) {
			int i = (int) (Math.random() * 10000);
			String nom = getName();
			System.out.print(nom);
			synchronized(compte) {
				compte.ajouter(i);
				compte.retirer(i);
			}
			
			int solde = compte.getSolde();
			System.out.print(nom);
			if (solde != 0) {
				System.out.println(nom + ":**solde=" + solde);
				System.exit(1);
			}
		}
	}

	public static void main(String[] args) {
		Compte compte = new Compte();
		for (int i = 0; i < 20; i++) {
			Operation operation = new Operation("" + (char) ('A' + i), compte);
			operation.start();
		}
	}
}
