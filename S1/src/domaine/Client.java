package domaine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Client implements Iterable<Commande> {
	private int numero;
	private String nom;
	private String prenom;
	private String tel;
	private Commande commandeEnCours;
	private List<Commande> commandesPasses;
	public Client(int numero, String nom, String prenom, String tel) {
		super();
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.commandesPasses = new ArrayList<Commande>();
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Commande getCommandeEnCours() {
		return commandeEnCours;
	}
	public boolean ajouterCommandeEnCours(Commande commande) {
		if(commandeEnCours == null) {
			commandeEnCours=commande;
			//System.out.println("Commande ajoutée");
			return true;
		}
		//System.out.println("Commande non ajoutée");
		return false;
	}
	public void cloturerCommande() throws NoCommandeEnCoursException {
		if(commandeEnCours==null) {
			throw new NoCommandeEnCoursException();
		}
		ajouterCommande(commandeEnCours);
		commandeEnCours=null;
	}
	private boolean ajouterCommande(Commande commande) {
		if(commandesPasses.contains(commande)) {
			return false;
		}
		commandesPasses.add(commande);
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numero;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (numero != other.numero)
			return false;
		return true;
	}
	@Override
	public Iterator<Commande> iterator() {
		return commandesPasses.iterator();
	}
	@Override
	public String toString() {
		return "Client [numero=" + numero + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + "]";
	}
	
	
	
	
	
}
