package hack6;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public enum ToStringReflexif {
	INSTANCE;

	public static String toStringReflexif(Object o) {
		return toStringReflexif(o, 0);
	}

	@SuppressWarnings("rawtypes")
	private static String toStringReflexif(Object o, int pad) {

		Class clss = o.getClass();

		String resultat = clss.getCanonicalName() + "[\n";
		
		int padInterne = pad + clss.getCanonicalName().length();

		for (Method m : clss.getDeclaredMethods()) {
			// TODO 1 tester que la méthode est un getter
			if (true) {
			
				resultat += spaces(padInterne)
						+ m.getName().substring(3, 4).toLowerCase()
						+ m.getName().substring(4);
				try {
				
					Class typeRetour = m.getReturnType();
				
					Object valeur = m.invoke(o);
					
					// Cas 1 : le type de retour est primitif
					String formate = toStringReflexifPrimitif(typeRetour,
							valeur);
					if ("".equals(formate)) {
					// Cas 2 : retour est java.lang.* formatté=
						formate = toStringReflexifJavaLang(typeRetour, valeur);
					}					
					if ("".equals(formate)) {
					// Cas 3 : retour est un tableau
						formate = toStringReflexifTableau(typeRetour, valeur,
								m, padInterne);
					}
					// Cas général
					if ("".equals(formate)) {
						//TODO 4 implémenter le cas général quand la valeur est une référence.
					}

					resultat += "=" + formate;
					} catch (IllegalArgumentException e) {
					throw new InternalError(
							"Impossible d'arriver ici, on n'appelle que des méthodes sans paramètres");
				} catch (IllegalAccessException e) {
					throw new InternalError(
							"Impossible d'arriver ici, on n'appelle que des méthodes appelables");
				} catch (InvocationTargetException e) {
					// TODO 5 implémenter l'affichage de l'exception
					
				}
				resultat += "\n";
			}
		}
		// Fin du formattage du résultat
		return resultat + spaces(pad) + "]";
	}
	
	@SuppressWarnings("rawtypes")
	private static String toStringReflexifPrimitif(Class typeRetour,
			Object valeur) {
		// TODO 2 implémenter la string affichée si la valeur est de type primitif, renvoyer "" sinon
		return "";
	}

	@SuppressWarnings("rawtypes")
	private static String toStringReflexifJavaLang(Class typeRetour,
			Object valeur) {
		// TODO 3 implémenter la string affichée si la valeur est de type String (ou java.lang), renvoyer "" sinon.
		return "";
	}

	@SuppressWarnings("rawtypes")
	private static String toStringReflexifTableau(Class typeRetour,
			Object valeur, Method m, int padInterne) {
		String resultat = "";
		if (typeRetour.isArray()) {
			String sep = "";
			resultat += "(";
			for (int i = 0; i < Array.getLength(valeur); i++) {
				resultat += sep
						+ toStringReflexif(Array.get(valeur, i), padInterne
								+ m.getName().length() - 1);
				sep = ",\n" + spaces(padInterne + m.getName().length() - 1);
			}
			resultat += ")";
		}
		return resultat;
	}



	/**
	 * Méthode utilitaire créant une chaîne de caractère de ipad espaces.
	 */
	private static String spaces(int ipad) {
		String p = "";
		for (int i = 0; i < ipad; i++)
			p += " ";
		return p;
	}

}
