package hack6;

public class DomaineB {
	private Integer id;
	private static int id_base = 0;

	public DomaineB() {
		id = id_base++;
	}

	public Integer getId() {
		return id;
	}
}
