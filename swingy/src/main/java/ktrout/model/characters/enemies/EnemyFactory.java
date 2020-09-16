package ktrout.model.characters.enemies;

public class EnemyFactory {
	
	public Enemies newEnemy(String type) {
		switch(type) {
		case "DALEK":
			return new Dalek();
		case "EVIL CLERIC":
			return new EvilCleric();
		case "EVIL ELF":
			return new EvilElf();
		case "EVIL SIBLING":
			return new EvilSibling();
		case "MAD SCIENTIST":
			return new MadScientist();
		case "ROQUE NINJA":
			return new RoqueNinja();
		default:
			throw new IllegalArgumentException("Invalid Enemy Class");
		}
	}
}
