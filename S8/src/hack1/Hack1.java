package hack1;

import java.lang.reflect.Field;

public class Hack1 {

	public static void main(String[] args) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		Class<?> classe = Class.forName("hack1.Employe");
		for(Field f : classe.getDeclaredFields()) {
			f.setAccessible(true);
			System.out.print("Field name--->"+f.getName());
			System.out.print("	Field type--->"+f.getType());
			System.out.print("	Field Value--->"+f.get(classe.newInstance()));
			System.out.println();
			
		}

	}

}
