package des_petits_exercices;

class Compteur {
	private int compteur = 0;

	public int getCompteur() {
		return compteur;
	}
	public void setCompteur(int compteur) {
		this.compteur = compteur;
		System.out.println("compteur vaut " + compteur);
	}


}

class VoteurThread extends Thread {
	private static Compteur compteur = new Compteur();

	public void run() {
		for (int i = 0; i < 100; i++) {
			synchronized(compteur) {
				int v = compteur.getCompteur();	
				try {
					Thread.sleep(10);// dormir 10 msec
				} catch (InterruptedException e) {
					interrupt();
				}
				compteur.setCompteur(v+1);
			}
			
		}
	}

	public static void main(String[] args) {
		Thread thread1 = new VoteurThread(); 
		Thread thread2 = new VoteurThread();
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
		}

		System.out.println("Compteur total : " +VoteurThread.compteur.getCompteur());
	}
}
