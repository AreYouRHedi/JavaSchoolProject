package tirage;

public class TirageFactoryImpl implements TirageFactory {

	@Override
	public Tirage getTirage() {
		return new TirageImpl();
	}

	@Override
	public Membre getMembre() {
		return new MembreImpl();
	}

}
