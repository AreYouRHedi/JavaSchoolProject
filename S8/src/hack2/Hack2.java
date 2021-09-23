package hack2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Hack2 {

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Class classe = Class.forName("hack2.Mari");
		for(Method method : classe.getDeclaredMethods()) {
			method.setAccessible(true);
			System.out.print("Field name--->"+method.getName());
			System.out.print("	Field type--->"+method.getReturnType().getCanonicalName());
			if(method.getReturnType().getCanonicalName().equals("int[]")) {
				int [] tab = (int[]) method.invoke(classe.newInstance());
				System.out.print("	Field Value--->");
				for(int val : tab) {
					System.out.print(val+"/");
				}
			}
			else {
				System.out.print("	Field Value--->"+method.invoke(classe.newInstance()));
			}
			
			System.out.println();
		}

	}

}
