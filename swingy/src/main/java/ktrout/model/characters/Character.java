package ktrout.model.characters;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ktrout.model.characters.Enemies;
import ktrout.model.Game;

public abstract class Character {

	private Game game;
	
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
		this.name = name;
		this.atk = atk;
		this.def = def;
		this.hp = hp;
	}
    // if attack > enemy's def -> enemy hp = enemy hp - (hero's atk - enemy's def)
	public void atk(Character enemy) {
	    int combatAtk = this.atk - enemy.def;
		if (this.atk > enemy.def)
			enemy.setHp(enemy.getHp() - combatAtk);
		else if (game.randomIntFromInterval(0, 10) <= 2)
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
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getAtk() {
		return atk;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getDef() {
		return def;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getHp() {
		return hp;
	}
}
