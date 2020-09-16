package ktrout.model.artifacts;

public class Weapon extends Artifact {

	public Weapon(String name) {
		super(name);
		switch(name) {
		case "BRONZE DAGGER":
			this.atk = 1;
		case "BONE DAGGER":
			this.atk = 10;
		case "FIRELORD KEYBLADE":
			this.atk = 10;
		case "IRON FLAIL":
			this.atk = 20;
		case "BOW AND ARROWS":
			this.atk = 10;
		case "WAR SCYTHE":
			this.atk = 10;
		case "BLUNT SWORD":
			this.atk = 1;
		}
	}
}
