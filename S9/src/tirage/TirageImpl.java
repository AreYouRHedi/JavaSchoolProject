package tirage;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import exceptions.TirageImpossible;

public class TirageImpl implements Tirage {

	private String nom;
	private Set<Membre> membres = new HashSet<>();
	private Set<Groupe> groupes;

	// nom et resultat du thread gagnant.
	public String gagnant;
	private Map<Membre, Membre> resultats;

	public TirageImpl(String nom) {
		super();
		this.nom = nom;
		this.groupes = new HashSet<>();
	}

	public TirageImpl() {
		super();
		//this.mapMembres = new HashMap<>();
		this.groupes = new HashSet<>();
	}

	public String getNom() {
		return this.nom;
	}

	@Override
	public void setNom(String nom) {
		this.nom = nom;

	}

	public Set<Membre> getMembres() {
		return Collections.unmodifiableSet(this.membres);
	}

	public Map<Membre, Membre> getResultats() {
		return Collections.unmodifiableMap(this.resultats);
	}

	public String getGagnant() {
		return gagnant;
	}

	/*
	 * renvoie le nombre de groupes qui contiennent le membre passé en paramètre
	 */
	// TODO partie 1 Completez cette methode en utilisant un Stream (une seule
	// instruction)
	public long nbPresentDansUnGroupe(String mail) {
		return groupes.stream().filter(m->m.contient(mail)).count();
	}

	/*
	 * indique si un membre est présent dans tous les groupes
	 */
	// TODO partie 1 Completez cette methode en utilisant un Stream (une seule
	// instruction)
	public boolean presentDansTousLesGroupes(String mail) {
		return groupes.stream().allMatch(m->m.contient(mail));
	}

	/*
	 * renvoie des statistiques basiques sur les groupes (nombres de membres moyens,
	 * maximum, ...)
	 */
	// TODO partie 1 Completez cette methode en utilisant un Stream (une seule
	// instruction)
	public IntSummaryStatistics statistiqueDesGroupes() {
		return groupes.stream().collect(Collectors.summarizingInt(m->m.getMembres().size()));

	}

	/*
	 * renvoie un membre aleatoire parmi une liste fournie en parametre.
	 */
	public static Membre getRandomMembre(List<Membre> membres) {
		int nextInt = new Random().nextInt(membres.size());
		return membres.get(nextInt);
	}

	/*
	 * ajoute les membres en parametres dans un nouveau groupe. Les membres en
	 * parametres doivent se trouver dans le tirage (si pas renvoyer false). Il ne
	 * peut y avoir de groupe de 1 membre.
	 */
	// TODO partie 1 Complétez cette methode.
	public boolean ajouterGroupe(Membre... membres) {
		if(membres.length <=1) {
			return false;
		}
		Groupe groupe = new Groupe();
		for(Membre membre : membres) {
			if(this.membres.contains(membre)) {
				if(!groupe.ajouter(membre)) {
					return false;
				}
			}
			else {
				return false;
			}
		}
		return groupes.add(groupe);
	}

	/*
	 * ajoute un membre au tirage
	 * 
	 */
	// TODO partie 1 Complétez cette méthode.
	public boolean ajouter(Membre membre) {
		return membres.add(membre);
	}

	/*
	 * crée un thread dont le nom est fourni en paramètre
	 */
	// TODO partie 2 Complétez cette méthode.
	public Thread getThread(String name) {
		return new TirageThread(name);
	}

	public boolean estFini() {
		return this.resultats != null;
	}

//TODO partie 2 implémentez TirageThread
	private class TirageThread extends Thread {
		private String nom;
		private Map<Membre,Membre> mapMembres = new HashMap<Membre,Membre>();
		private List<Membre> membresPasTires= membres.stream().collect(Collectors.toList());
		
		private TirageThread(String nom) {
			this.nom=nom;
		}
		@Override
		public void run() {
			while(!membresPasTires.isEmpty() && !TirageImpl.this.estFini()) {
					Membre membre = getRandomMembre(membresPasTires); 
					membresPasTires.remove(membre);
					tirer(membre);
			}
			synchronized (TirageImpl.this) {
				if (TirageImpl.this.resultats == null) {
					TirageImpl.this.resultats = this.mapMembres;
					TirageImpl.this.gagnant = ((TirageThread) currentThread()).nom;
				}
			}
		}
		private void tirer(Membre membre) throws TirageImpossible {
			
			Membre membreTire = getRandomMembre(membresPasTires);
			if(groupes.stream().noneMatch(g->g.contient(membre.getMail()) && g.contient(membreTire.getMail()))) {
			
				membresPasTires.remove(membreTire);
				mapMembres.put(membre, membreTire);
			}
			
			
		}
		
	}

}
