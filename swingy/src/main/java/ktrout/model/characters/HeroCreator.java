package ktrout.model.characters;

import ktrout.model.artifacts.Armor;
import ktrout.model.artifacts.Helm;
import ktrout.model.artifacts.Weapon;

public class HeroCreator {

	private int id;
	private String name;
	private int atk;
	private int def;
	private int hp;
	private String heroClass;
	private int lvl;
	private int exp;
	private Weapon weap;
	private Armor armor;
	private Helm helm;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAtk(int atk) {
		this.atk = atk;
	}
	
	public void setDef(int def) {
		this.def = def;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void setHeroClass (String heroClass) {
		this.heroClass = heroClass;
	}
	
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public void setWeap(Weapon weap) {
		this.weap = weap;
	}
	
	public void setArmor(Armor armor) {
		this.armor = armor;
	}
	
	public void setHelm(Helm helm) {
		this.helm = helm;
	}
	
	public CreateHero getHero() {
		return new CreateHero(name, atk, def, hp, id, heroClass, lvl, exp, weap, armor, helm);
	}
}
