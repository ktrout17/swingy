package ktrout.model.characters;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ktrout.model.characters.Enemies;

public abstract class Character {

	@NotNull(message = "Name cannot be null.")
	@Size(min = 1, max = 12, message = "Name must be minimum 1 character and maxmimum 12 characters.")
	protected String name;

	@Min(value = 0, message = "Attack cannot be less than zero.")
	protected int atk;

	@Min(value = 0, message = "Defense cannot be less than zero.")
	protected int def;

	@Min(value = 1, message = "HP cannot be less than one.")
	protected int hp;

	public Character(String name, int atk, int def, int hp) {
		name = this.name;
		atk = this.atk;
		def = this.def;
		hp = this.hp;
	}
    // if attack > enemy's def -> enemy hp = enemy hp - (hero's atk - enemy's def)
	public void atk(Character enemy) {
	    int combatAtk = this.atk - enemy.def;
		if (this.atk > enemy.def)
			enemy.setHp(enemy.getHp() - combatAtk);
		else if ((int)(Math.random() * 10) <= 2)
		    enemy.setHp(enemy.getHp() - this.atk);
	}

	public boolean combat(Character enemy) {
	    while (enemy.getHp() > 0 && this.getHp() > 0) {
	        this.atk(enemy);
	        enemy.atk(this);
        }
	    return this.getHp() > 0;
    }

	public void setName(String name) {
		name = this.name;
	}

	public String getName() {
		return name;
	}

	public void setAtk(int atk) {
		atk = this.atk;
	}

	public int getAtk() {
		return atk;
	}

	public void setDef(int def) {
		def = this.def;
	}

	public int getDef() {
		return def;
	}

	public void setHp(int hp) {
		hp = this.hp;
	}

	public int getHp() {
		return hp;
	}
}
