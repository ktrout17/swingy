package ktrout.model.artifacts;

public class Armor extends Artifact {

	public Armor(String name, int points) {
		super(name, points);
		switch(name) {
		case "ENCHANTED ANGEL WINGS":
			points = 5;
		case "RED CAPE":
			points = 1;
		case "FIRELORD CHAINMAIL":
			points = 15;
		case "LEATHER GLOVES":
			points = 1;
		case "BRONZE PANTS":
			points = 1;
		case "FIRELORD BOOTS":
			points = 10;
		case "BRONZE SHIELD":
			points = 10;
		}
	}
}
