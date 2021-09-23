package exercices;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercice5 {

	public static void main(String[] args) {
		try (Stream<String> lignes = Files.lines(Paths.get("etudiants.csv"), Charset.defaultCharset())) {
			// lignes.limit(1).forEach(System.out::println);
			List<Etudiant> etudiants = lignes.map(m -> new Etudiant(m)).collect(Collectors.toList());
			// etudiants.forEach(System.out::println);
			/*
			 * List<Etudiant> etudiants2= lignes.map(m->new
			 * Etudiant(m.substring(m.lastIndexOf(';')+1),m.substring(0,m.indexOf(';')-1))).
			 * collect(Collectors.toList()); etudiants2.forEach(System.out::println);
			 */
			// etudiants.stream().filter(m->m.getLastName().length()<6 &&
			// (m.getLastName().indexOf('I')!=-1 ||
			// m.getLastName().indexOf('Y')!=-1)).forEach(System.out::println);
			//System.out.println(etudiants.stream().filter(m->contientAE(m.getFirstName())).map(m->m.getFirstName()).findFirst().orElse("No match"));
			//System.out.println(etudiants.stream().allMatch(m->!(m.getEmail().equals(""))));
			//System.out.println(etudiants.stream().filter(m->m.getFirstName().equals("Kevin")).map(m->m.getIdNumber()).findFirst().orElse("Pas trouvé"));
			//System.out.println(lignes.count());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static boolean contientAE(String mot) {
		if(mot.indexOf("ae")!=-1) {
			return true;
		}
		return false;
	}

}
