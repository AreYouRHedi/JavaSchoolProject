package domaine;


public class LocalImpl implements Local {

  private String nom;
  private int capacite;

  @Override
public String getNom() {
    return nom;
  }

  @Override
public void setNom(String nom) {
    this.nom = nom;
  }

  @Override
public int getCapacite() {
    return capacite;
  }

  @Override
public void setCapacite(int capacite) {
    this.capacite = capacite;
  }

}
