package domaine;

import java.util.List;

public class ProfesseurImpl implements Professeur {

  private String nom;
  private List<Cours> cours;

  @Override
public String getNom() {
    return nom;
  }

  @Override
public void setNom(String nom) {
    this.nom = nom;
  }

  @Override
public List<Cours> getCours() {
    return cours;
  }

  @Override
public void setCours(List<Cours> cours) {
    this.cours = cours;
  }

}
