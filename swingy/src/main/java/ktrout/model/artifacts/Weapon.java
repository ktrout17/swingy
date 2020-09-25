package ktrout.model.artifacts;

public class Weapon extends Artifact {

	public Weapon(String name, int points) {
		super(name, points);
		switch(name) {
		case "BRONZE DAGGER":
			points = 1;
		case "BONE DAGGER":
			points = 10;
		case "FIRELORD KEYBLADE":
			points = 10;
		case "IRON FLAIL":
			points = 20;
		case "BOW AND ARROWS":
			points = 10;
		case "WAR SCYTHE":
			points = 10;
		case "BLUNT SWORD":
			points = 1;
		}
	}
}
