package ktrout.model.artifacts;

public abstract class Artifact {
	
	private int points;
	private String name;
	
	public Artifact(String name, int points) {
		
		points = this.points;
		name = this.name;
	}
	
	public int getPoints() {
		return points;
	}
	
	public String getName() {
		return name;
	}
}
