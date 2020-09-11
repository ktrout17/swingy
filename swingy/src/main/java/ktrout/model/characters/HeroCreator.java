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
	
	public void setHeroClass (String heroClass) {
		heroClass = this.heroClass;
	}
	
	public void setLvl(int lvl) {
		lvl = this.lvl;
	}
	
	public void setExp(int exp) {
		exp = this.exp;
	}
	
	public void setWeap(Weapon weap) {
		weap = this.weap;
	}
	
	public void setArmor(Armor armor) {
		armor = this.armor;
	}
	
	public void setHelm(Helm helm) {
		helm = this.helm;
	}
	
	public CreateHero getHero() {
		return new CreateHero(name, atk, def, hp, id, heroClass, lvl, exp, weap, armor, helm);
	}
}
