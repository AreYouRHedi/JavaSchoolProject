package domaine;

public class NoCommandeEnCoursException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoCommandeEnCoursException() {
		System.out.println("Pas de commande en cours");
	}
}
