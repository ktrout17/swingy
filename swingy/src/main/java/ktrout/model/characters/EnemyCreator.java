package ktrout.model.characters;

public class EnemyCreator {
	
	private int id;
	private String name;
	private int atk;
	private int def;
	private int hp;
	private String enemyClass;
	
	public void setId(int id) {
		id = this.id;
	}
	
	public void setName(String name) {
		name = this.name;
	}
	
	public void setAtk(int atk) {
		atk = this.atk;
	}
	
	public void setDef(int def) {
		def = this.def;
	}
	
	public void setHp(int hp) {
		hp = this.hp;
	}
	
	public void setEnemyClass(String enemyClass) {
		enemyClass = this.enemyClass;
	}
	
	public Enemy getEnemy() {
		return new Enemy(name, atk, def, hp);
	}
}
