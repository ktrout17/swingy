package ktrout.model.artifacts;

public class Armor extends Artifact {

	public Armor(String name) {
		super(name);
		switch(name) {
		case "ENCHANTED ANGEL WINGS":
			this.def = 5;
		case "RED CAPE":
			this.def = 1;
		case "BRONZE CHAINMAIL":
			this.def = 1;
		case "FIRELORD CHAINMAIL":
			this.def = 15;
		case "LEATHER GLOVES":
			this.def = 1;
		case "STEEL GLOVES":
			this.def = 45;
		case "BRONZE PANTS":
			this.def = 1;
		case "FIRELORD BOOTS":
			this.def = 10;
		case "BRONZE SHIELD":
			this.def = 10;
		}
	}
}
