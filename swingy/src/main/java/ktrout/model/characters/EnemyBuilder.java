package ktrout.model.characters;

public class EnemyBuilder {
	
	private static EnemyCreator createNew(String name) {
		EnemyCreator creator = new EnemyCreator();
		creator.setName(name);
		return creator;
	}
	
	public static Enemy createEvilSibling(String name) {
		EnemyCreator creator = createNew(name);
		creator.setId(0);
		creator.setAtk(40);
		creator.setDef(30);
		creator.setHp(80);
		creator.setEnemyClass("Evil Sibling");
		return creator.getEnemy();
	}
	
	public static Enemy createMadScientist(String name) {
		EnemyCreator creator = createNew(name);
		creator.setId(1);
		creator.setAtk(60);
		creator.setDef(40);
		creator.setHp(100);
		creator.setEnemyClass("Mad Scientist");
		return creator.getEnemy();
	}
	
	public static Enemy createRoqueNinja(String name) {
		EnemyCreator creator = createNew(name);
		creator.setId(2);
		creator.setAtk(30);
		creator.setDef(10);
		creator.setHp(80);
		creator.setEnemyClass("Roque Ninja");
		return creator.getEnemy();
	}
	
	public static Enemy createDalek(String name) {
		EnemyCreator creator = createNew(name);
		creator.setId(3);
		creator.setAtk(70);
		creator.setDef(20);
		creator.setHp(70);
		creator.setEnemyClass("Dalek");
		return creator.getEnemy();
	}
	
	public static Enemy createEvilElf(String name) {
		EnemyCreator creator = createNew(name);
		creator.setId(4);
		creator.setAtk(20);
		creator.setDef(20);
		creator.setHp(90);
		creator.setEnemyClass("Evil Elf");
		return creator.getEnemy();
	}
	
	public static Enemy createEvilCleric(String name) {
		EnemyCreator creator = createNew(name);
		creator.setId(5);
		creator.setAtk(30);
		creator.setDef(30);
		creator.setHp(80);
		creator.setEnemyClass("Evil Cleric");
		return creator.getEnemy();
	}
}

