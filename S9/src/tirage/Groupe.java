package tirage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class Groupe {
	
	private Set<Membre> membres = new HashSet<>();

	boolean ajouter(Membre membre) {
		return this.membres.add(membre);
	}

	boolean supprimer(Membre membre) {
		return this.membres.remove(membre);
	}
	/*
	 *  renvoie true si le mail en parametre correspond à celui de l'un des membres du groupe
	 */
	//TODO Complétez cette m�thode en utilisant un Stream (une seule instruction)
	boolean contient(String mail) {
		return membres.stream().anyMatch(m->m.getMail().equals(mail));
	}
	
	Set<Membre> getMembres() {
		return Collections.unmodifiableSet(this.membres);
	}

}