package exercices;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Exercice3 {

	public static void main(String[] args) {
		List<String> words = Arrays.asList("hi","hello","hola", "bye", "goodbye", "adios");
		//words.forEach(System.out::println);
		//words.stream().map(m->m+"!").forEach(System.out::println);
		/*
		String[] tab = words.stream().toArray(String[]::new);
		for(int i=0; i<tab.length;i++) {
			System.out.println(tab[i]);
		}
		*/
		//words.stream().map(m->m.toUpperCase()).sorted(Comparator.reverseOrder()).forEach(System.out::println);
		/*
		String word = words.stream().filter(m->m.length()%2==0).collect(Collectors.joining(", "));
		System.out.println(word);
		*/
		//System.out.println(exercice6(words, 'i'));
		//System.out.println(words.stream().map(m->m.toUpperCase()).collect(Collectors.joining(" ")));
		//System.out.println(words.stream().collect(Collectors.joining(" ")).toUpperCase());
		//System.out.println(words.stream().collect(Collectors.joining()).length());
		//System.out.println(words.stream().filter(m->m.indexOf('e')!=-1).count());
	}

	private static String exercice6(List<String> words, char c) {
		 String word = words.stream().filter(m->m.length()<4 && m.indexOf(c)!=-1).map(m->m.toUpperCase()+" TO UPPER").limit(1).collect(Collectors.joining());
		 if(word.length()==0) {
			 return "No match";
		 }
		return word;
	}

}
