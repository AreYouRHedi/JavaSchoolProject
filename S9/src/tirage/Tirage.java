package tirage;

import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public interface Tirage {

	String getNom();

	void setNom(String nom);

	Set<Membre> getMembres();

	Map<Membre, Membre> getResultats();

	boolean estFini();
	
	String getGagnant();

	long nbPresentDansUnGroupe(String mail);

	boolean presentDansTousLesGroupes(String mail);

	IntSummaryStatistics statistiqueDesGroupes();

	boolean ajouterGroupe(Membre... membres);

	boolean ajouter(Membre membre);

	Thread getThread(String name);

}