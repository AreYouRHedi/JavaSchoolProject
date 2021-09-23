package domaine;

import java.util.ArrayList;
import java.util.List;

public class EcoleImpl implements Ecole {

  private List<Cours> cours = new ArrayList<Cours>();
  private List<Local> locaux = new ArrayList<Local>();
  private List<Professeur> professeurs = new ArrayList<Professeur>();

  @Override
public boolean ajouterCours(Cours cours) {
    return this.cours.add(cours);
  }

  @Override
public boolean ajouterLocal(Local local) {
    return this.locaux.add(local);
  }

  @Override
public boolean ajouterProfesseur(Professeur professeur) {
    return this.professeurs.add(professeur);
  }

}
