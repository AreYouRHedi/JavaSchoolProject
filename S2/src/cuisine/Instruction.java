package cuisine;

import java.time.Duration;
import static util.Util.*;
public class Instruction implements Cloneable {
	private String description;
	private Duration dureeEnMinutes;
	
	public Instruction(String description, int duree) {
		checkString(description);
		checkPositiveOrZero(duree);
		this.description=description;
		this.dureeEnMinutes=Duration.ofMinutes(duree);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		checkString(description);
		this.description = description;
	}

	public Duration getDureeEnMinutes() {
		return dureeEnMinutes;
	}

	public void setDureeEnMinutes(Duration dureeEnMinutes) {
		checkObject(dureeEnMinutes);
		//checkPositive(dureeEnMinute.getSeconds());
		this.dureeEnMinutes = dureeEnMinutes;
	}

	@Override
	protected Instruction clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Instruction)super.clone();
	}
	@Override
	public String toString() {
		String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutes()%60);
		return "("+hms+") "+description;
	}
	
}
