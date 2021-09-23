package domaine;

public class EcoleFactoryImpl implements EcoleFactory {
	
	@Override
	public Cours getCours() {
		Cours cours=new CoursImpl();
		return cours;
	}
	@Override
	public Local getLocal() {
		Local local=new LocalImpl();
		return local;
	}
	@Override
	public Professeur getProfesseur() {
		Professeur prof = new ProfesseurImpl();
		return prof;
	}
	@Override
	public Ecole getEcole() {
		return new EcoleImpl();
	}
	
}
