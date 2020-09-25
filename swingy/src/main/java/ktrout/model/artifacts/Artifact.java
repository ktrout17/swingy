package ktrout.model.artifacts;

import javax.validation.constraints.Min;

public abstract class Artifact {
	
	@Min(value = 1)
	private String name;
	private int points;
	
	
	public Artifact(String name, int points) {
		name = this.name;
		points = this.points;
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
