package ktrout.model.artifacts;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

public abstract class Artifact {
	
	@NotNull(message = "Name cannot be null.")
	private String name;
	@Min(value = 0, message = "Points cannot be less than zero.")
	private int points;
	
	
	public Artifact(String name, int points) {
		this.name = name;
		this.points = points;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPoints() {
		return points;
	}

	@Override
    public String toString() {
        return name + " (+" + points + ")";
    }
}
