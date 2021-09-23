package exercices;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Exercice6Transactions {
	 public enum  TransactionsLevel {VeryHi,Hi,Lo,Me};
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
       
        Exercice6Transactions main = new Exercice6Transactions(transactions);
        main.run();
    }

    /**
     * La liste de base de toutes les transactions.
     */
    private List<Transaction> transactions;

    /**
     * Crée un objet comprenant toutes les transactions afin de faciliter leur usage pour chaque point de l'énoncé
     * @param transactions la liste des transactions
     */
    public Exercice6Transactions(List<Transaction> transactions){
        this.transactions = transactions;
    }

    public void run(){
        //this.point1();
        //this.point2();
        //this.point3();
        //this.point4();
        this.point5();
        this.point6();
    }

    private void point2() {
		// TODO Auto-generated method stub
    	Map<Trader,Long> traders = transactions.stream().collect(Collectors.groupingBy(Transaction::getTrader, Collectors.counting()));
		System.out.println(traders.toString());
	}
	private void point3() {
		Map<Trader,Transaction> traders = transactions.stream().collect(Collectors.groupingBy(Transaction::getTrader, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Transaction::getValue)),Optional::get)));
	}
	private void point4() {
		// TODO Auto-generated method stub
		Map<String,Map<Trader,List<Transaction>>> villes = transactions.stream().collect(Collectors.groupingBy(m->m.getTrader().getCity(),Collectors.groupingBy(m->m.getTrader())));
		System.out.println(villes.toString());
	}
	
	private void point5() {
		Map<TransactionsLevel,List<Transaction>> trans = transactions.stream().collect(Collectors.groupingBy(t-> {
			if(t.getValue() >= 1000) return TransactionsLevel.VeryHi;
			else if(t.getValue() >= 800 && t.getValue() <1000) return TransactionsLevel.Hi;
			else if(t.getValue() >=600 && t.getValue() <800) return TransactionsLevel.Me;
			else return TransactionsLevel.Lo;
		}));
		
	}

	private void point6() {
		Map<Boolean, List<Transaction>> trans = transactions.stream().collect(Collectors.partitioningBy(m->m.getTrader().getCity().equals("Cambridge")));
		
	}

	private void point1(){
    	Map<Trader, List<Transaction>> traders = transactions.stream().collect(Collectors.groupingBy(Transaction::getTrader));
    }
}
