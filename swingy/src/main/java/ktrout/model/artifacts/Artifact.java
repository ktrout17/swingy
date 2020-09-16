package ktrout.model.artifacts;

import javax.validation.constraints.Min;

public abstract class Artifact {
	
	@Min(value = 1)
	private String name;
	protected int atk;
	protected int def;
	protected int hp;
	
	
	public Artifact(String name) {
		name = this.name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAtk() {
		return this.atk;
	}
	
	public int getDef() {
		return this.def;
	}
	
	public int getHp() {
		return this.hp;
	}
}
