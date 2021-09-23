package main;
import domaine.Cours;
import domaine.Ecole;
import domaine.EcoleFactory;
import domaine.Local;
import domaine.Professeur;

public class GestionEcole {
	private EcoleFactory factory;
	
	public GestionEcole(EcoleFactory factory) {
		this.factory=factory;
	}

  public void gererEcole() {
    // Crée une école
	  
    Ecole ecole = factory.getEcole();
    Cours cours = factory.getCours();
    cours.setNom("Ateliers Java");
    ecole.ajouterCours(cours);
    Local local = factory.getLocal();
    local.setNom("A017");
    local.setCapacite(16);
    ecole.ajouterLocal(local);
    Professeur professeur = factory.getProfesseur();
    professeur.setNom("Emeline Leconte");
    ecole.ajouterProfesseur(professeur);
    // Calcule un horaire
    // ... plein de code compliqué...
    System.out.println("Horaire trouvé !");
  }

}
