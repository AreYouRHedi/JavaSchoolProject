package exercices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Exercice1 {

	public static void main(String[] args) {
		List<String> words = Arrays.asList("hi","hello","hola", "bye", "goodbye", "adios");
		List<String> exercice1=allMatches(words, n->n.indexOf('o')!=-1);
		//exercice1.forEach(System.out::println);
		List<Integer> nums = Arrays.asList(1, 10, 100, 1000, 10000);
		List<Integer> exercice2 = allMatches(nums, n -> n>500);
		//exercice2.forEach(System.out::println);
		List<String> transformedList=transformedList(words,n->n.toUpperCase());
		//transformedList.forEach(System.out::println);
		List<Integer> wordLengths = transformedList(words, String::length);
		List<Double> inverses = transformedList(nums, i -> 1.0/i);
		wordLengths.forEach(System.out::println);
		inverses.forEach(System.out::println);
	}


	private static <R, E> List<E> transformedList(List<R> words, Function<R,E> function) {
		return words.stream().map(function).collect(Collectors.toList());
	}


	private static <E> List<E> allMatches(List<E> words, Predicate<E> predicat) {
		return words.stream().filter(predicat).collect(Collectors.toList()) ;                                                                        
		
	}

}
