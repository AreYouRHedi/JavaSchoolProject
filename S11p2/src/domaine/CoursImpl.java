package domaine;

public class CoursImpl implements Cours {

  private String nom;

  @Override
public String getNom() {
    return nom;
  }

  @Override
public void setNom(String nom) {
    this.nom = nom;
  }

}
