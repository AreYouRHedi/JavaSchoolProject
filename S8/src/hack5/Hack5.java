package hack5;

import java.lang.reflect.InvocationTargetException;

public class Hack5 {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException {
		Class classe = Class.forName("hack5.Singleton");
		Singleton sing = (Singleton) classe.newInstance();
		Singleton sing2=(Singleton) classe.getConstructors()[0].newInstance();
		

	}

}
