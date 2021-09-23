package exercices;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

//import jdk.nashorn.internal.codegen.MethodEmitter;

public class Exercice4 {
	
	public static void main(String[] args) {
		/*
		double[] tab = new Random().doubles(10, 0, 100).toArray();
		System.out.println("---------------");
		for(double valeur : tab) {
			System.out.println(valeur);
		}
		System.out.println("---------------");
		System.out.println("Racine carrée :"+DoubleStream.of(tab).map(m->Math.sqrt(m)).sum());
		*/
		
		/*
		methode1(100).limit(5).forEach(System.out::println);
		methode2(100).limit(5).forEach(System.out::println);
		*/
		methode1(100).limit(10).boxed().collect(Collectors.toList());
		methode2(100).limit(100).collect(Collectors.toList());
		double[] tab2=methode1(100).limit(20).toArray();
		//double [] tab3=methode2(100).limit(20).toArray(double[]::new);
		
	}
	private static DoubleStream methode1(int valeurMax) {
		return new Random().doubles(0, valeurMax);
	}
	private static Stream<Double> methode2(int valeurMax) {
		return Stream.generate(()->Math.random()*valeurMax);
	}
}
