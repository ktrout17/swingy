package ktrout.model.characters;

public class EnemyFactory {
	
	public static Enemy newEnemy(String name, int id) {
		switch(id) {
		case 0:
			return EnemyBuilder.createEvilSibling(name);
		case 1:
			return EnemyBuilder.createMadScientist(name);
		case 2:
			return EnemyBuilder.createRoqueNinja(name);
		case 3:
			return EnemyBuilder.createDalek(name);
		case 4:
			return EnemyBuilder.createEvilElf(name);
		case 5:
			return EnemyBuilder.createEvilCleric(name);
		default:
			throw new IllegalArgumentException("Invalid enemy.");
		}
	}
}
