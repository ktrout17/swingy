package ktrout.model.characters.enemies;

public abstract class Enemies {
	
	protected String name;
	protected int hp;
	protected int atk;
	protected int def;
	
	public int atk(int points) {
		return atk + points;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getHp() {
		return this.hp;
	}
	
	public int getAtk() {
		return this.atk;
	}
	
	public int getDef() {
		return this.def;
	}
	
	public void getHit(int damage) {
		this.hp = this.hp - damage;
	}
}
