package test;
import tirage.Membre;
import tirage.Tirage;
import tirage.TirageFactory;
import tirage.TirageFactoryImpl;

public class Main {

	public static void main(String[] args) {
		
		// Test partie 1
		TirageFactory tirageFactory = new TirageFactoryImpl();
		Tirage tirage = tirageFactory.getTirage();
		tirage.setNom("premier");
		for (int i = 0; i < 100; i++) {
			Membre membre = tirageFactory.getMembre();
			membre.setNom("nom " + i);
			membre.setPrenom("prenom " + i);
			membre.setMail("mail " + i);
			tirage.ajouter(membre);
		}
		Membre membrePasDansLeTirage = tirageFactory.getMembre();
		membrePasDansLeTirage.setNom("PasDansTirage");
		membrePasDansLeTirage.setPrenom("PasDansTirage");
		membrePasDansLeTirage.setMail("PasDansTirage");
		
		Membre[] arrayMembres = (Membre[]) tirage.getMembres().toArray(new Membre[0]);
		System.out.println(tirage.ajouterGroupe(arrayMembres[0], arrayMembres[1], arrayMembres[2])+"-->true");
		System.out.println(tirage.ajouterGroupe(arrayMembres[10], arrayMembres[11], arrayMembres[2], arrayMembres[12])+"-->true");
		System.out.println(tirage.ajouterGroupe(arrayMembres[0])+"-->false");
		System.out.println(tirage.ajouterGroupe(arrayMembres[0],arrayMembres[0])+"-->false");
		System.out.println(tirage.ajouterGroupe(membrePasDansLeTirage)+"-->false");
		
		System.out.println(tirage.presentDansTousLesGroupes(arrayMembres[2].getMail())+"-->true");
		System.out.println(tirage.presentDansTousLesGroupes(arrayMembres[1].getMail())+"-->false");
		
		System.out.println(tirage.nbPresentDansUnGroupe( arrayMembres[1].getMail())+"-->1");
		System.out.println(tirage.nbPresentDansUnGroupe( arrayMembres[67].getMail())+"-->0");
		
		System.out.println(tirage.statistiqueDesGroupes()+"-->{count=2, sum=7, min=3, average=3,500000, max=4}");
		

		// Test partie 2
		Thread tirageThread = tirage.getThread("tirage 1");
		Thread tirageThread2 = tirage.getThread("tirage 2");
		Thread tirageThread3 = tirage.getThread("tirage 3");
		tirageThread.start();
		tirageThread2.start();
		tirageThread3.start();

		while (!tirage.estFini()) {
			System.out.println("job");
		}

		tirage.getResultats().entrySet().forEach(entry -> System.out.println(((Membre) entry.getKey()).getMail() + " offre ра " + ((Membre) entry.getValue()).getMail()));
		System.out.println(tirage.getGagnant());
	}

}
