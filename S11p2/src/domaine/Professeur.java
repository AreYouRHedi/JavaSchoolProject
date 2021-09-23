package domaine;

import java.util.List;

public interface Professeur {

	String getNom();

	void setNom(String nom);

	List<Cours> getCours();

	void setCours(List<Cours> cours);

}