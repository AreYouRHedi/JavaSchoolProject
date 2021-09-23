package domaine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Commande implements Iterable<LigneCommande> {
	private int num;
	private LocalDate date;
	private static int numero;
	private Client client;
	private List<LigneCommande> lignesCommandes;
	public Commande(Client client) {
		super();
		numero++;
		this.num=numero;
		this.client = client;
		if(!client.ajouterCommandeEnCours(this)) {
			throw new IllegalArgumentException();
		}
		this.date=LocalDate.now();
		this.lignesCommandes= new ArrayList<LigneCommande>();
	}
	
	public int getNum() {
		return num;
	}

	public LocalDate getDate() {
		return date;
	}

	public Client getClient() {
		return client;
	}
	public double CalculerMontantTotal() {
		double prix=0.0;
		for(LigneCommande ligne : lignesCommandes) {
			prix+=ligne.calculerLigneDeCommande();
		}
		return prix;
	}
	public String afficherCommande() {
		String chaine="";
		for(LigneCommande ligne : lignesCommandes) {
			chaine+=ligne.toString()+"\n";
		}
		return chaine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
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
		Commande other = (Commande) obj;
		if (num != other.num)
			return false;
		return true;
	}
	@Override
	public Iterator<LigneCommande> iterator() {
		return lignesCommandes.iterator();
	}
	public boolean ajouterPizza(LigneCommande ligne) {
		if(lignesCommandes.contains(ligne)) {
			return false;
		}
		lignesCommandes.add(ligne);
		return true;
	}

	@Override
	public String toString() {
		return "Commande [num=" + num + ", date=" + date + ", client=" + client
				+ "]";
	}
	
	
}
