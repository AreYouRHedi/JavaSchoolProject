package hack4;

import java.lang.reflect.Field;

public class Hack4 {

	public static void main(String[] args) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		Class classe = Class.forName("hack4.Point");
		Point point = (Point) classe.newInstance();
		for(Field field : classe.getDeclaredFields()) {
			field.setAccessible(true);
			if(field.getType().getCanonicalName().equals("double")) {
				field.setInt(point, 19);
			}
			if(field.getType().getCanonicalName().equals("java.lang.String")) {
				field.set(point, "Hedi");
			}
			System.out.println(field.get(point));
		}

	}

}
