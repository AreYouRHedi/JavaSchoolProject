package exercices;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Exercice2Transactions {

	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));
		Exercice2Transactions main = new Exercice2Transactions(transactions);
		main.run();
	}

	/**
	 * La liste de base de toutes les transactions.
	 */
	private List<Transaction> transactions;

	/**
	 * Crée un objet comprenant toutes les transactions afin de faciliter leur usage
	 * pour chaque point de l'énoncé
	 *
	 * @param transactions la liste des transactions
	 */
	public Exercice2Transactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	/**
	 * Exécute chaque point de l'énoncé
	 */
	public void run() {
		//this.point1();
		//this.point2();
		//this.point3();
		//this.point4();
		//this.point5();
		//this.point6();
		//this.point7();
		this.point8();
		this.point9();
	}

	private void point9() {
		System.out.println(transactions.stream().collect(Collectors.summarizingInt(m->m.getValue())));
		
	}

	private void point8() {
		System.out.println(transactions.stream().collect(Collectors.minBy(Comparator.comparing(Transaction::getValue))).toString());
		
	}

	private void point7() {
		System.out.println(transactions.stream().mapToInt(d->d.getValue()).max());
		
	}

	private void point6() {
		transactions.stream().filter(d->d.getTrader().getCity().contentEquals("Cambridge")).map(d->d.getValue()).forEach(System.out::println);
		
	}

	private void point5() {
		boolean valeur = transactions.stream().anyMatch(m->m.getTrader().getCity().equals("Milan"));
		System.out.println(valeur);
	}

	private void point4() {
		// j'ai melangé avec le 3 lol
		
	}

	private void point3() {
		String traders = transactions.stream().filter(c->c.getTrader().getCity().equals("Cambridge")).map(c->c.getTrader().getName()).distinct().sorted().collect(Collectors.joining(", "));
		System.out.println(traders);
	}

	private void point2() {
		transactions.stream().map(c->c.getTrader().getCity()).distinct().forEach(System.out::println);
		
	}

	private void point1() {
		transactions.stream().filter(d->d.getYear()==2011).sorted(Comparator.comparing(Transaction::getValue)).forEach(System.out::println);
	}
}
