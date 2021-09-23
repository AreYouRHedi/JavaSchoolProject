package domaine;

import java.time.LocalDateTime;
import java.util.Date;

public class PizzaComposable extends Pizza {
	private LocalDateTime date;
	private Client createur;
	
	public PizzaComposable(Client createur) {
		super("Pizza de "+createur.getNom() + " "+createur.getPrenom(), "Pizza composable du client "+createur.getNumero());
		this.createur=createur;
		this.date=LocalDateTime.now();
	}

}
