package hack3;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Hack3 {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class classe = Class.forName("hack3.Etudiant");
		for (Constructor<?> constructor : classe.getDeclaredConstructors()) {
			constructor.setAccessible(true);
			if(constructor.getParameterCount() > 0) {
				System.out.println(constructor.newInstance("salut"));
			}
			else {
				System.out.println(constructor.newInstance());
			}
			
		}

	}

}
