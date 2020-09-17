package ktrout.model.characters;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import ktrout.model.artifacts.Armor;
import ktrout.model.artifacts.Helm;
import ktrout.model.artifacts.Weapon;

public class CreateHero extends Character {

	private Weapon weap;
	private Armor armor;
	private Helm helm;
	
	private int id;
	
	@Min(value = 0)
	private int lvl;
	
	@Min(value = 0)
	private int exp;
	
	@NotNull
	private String heroClass;
	
	public CreateHero(String name, int atk, int def, int hp, int id, String heroClass, int lvl, int exp, Weapon weap, Armor armor, Helm helm) {
		super(name, atk, def, hp);
		id = this.id;
		weap = this.weap;
		armor = this.armor;
		helm = this.helm;
		lvl = this.lvl;
		exp = this.exp;
		heroClass = this.heroClass;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		id = this.id;
	}
	
	public void equipHelm(Helm helm) {
		if (this.helm != null) {
			this.hp = this.hp - this.helm.getHp();
			if (this.hp + helm.getHp() <= 0) {
				this.hp = this.hp + this.helm.getHp();
				return;
			}
		}
		this.hp = this.hp + helm.getHp();
		helm = this.helm;
	}
	
	public void equipArmor(Armor armor) {
		if (this.armor != null)
			this.def = this.def - this.armor.getDef();
		this.def = this.def + this.armor.getDef();
		armor = this.armor;
	}
	
	public void equipWeap(Weapon weap) {
		if (this.weap != null)
			this.atk = this.atk - this.weap.getAtk();
		this.atk = this.atk + this.weap.getAtk();
		weap = this.weap;
	}
	
	// exp formula = level*1000+(level−1)2*450
	public void addExp(int addExp) {
		int nextLvl = lvl * 1000 + (lvl - 1) * (lvl - 1) * 450;
		
		if (exp + addExp >= nextLvl)
			levelUp();
		exp = exp + addExp;
	}
	
	private void levelUp() {
		lvl++;
		hp = hp + 40 + (lvl * 5);
		atk = atk + (lvl * 5);
		def = def + (lvl * 3);
	}
	
	public void setWeap(Weapon weap) {
		weap = this.weap;
	}
	
	public Weapon getWeap() {
		return weap;
	}
	
	public void setHelm(Helm helm) {
		helm = this.helm;
	}
	
	public Helm getHelm() {
		return helm;
	}
	
	public void setArmor(Armor armor) {
		armor = this.armor;
	}
	
	public Armor getArmor() {
		return armor;
	}
	
	public void setLvl(int lvl) {
		lvl = this.lvl;
	}
	
	public int getLvl() {
		return lvl;
	}
	
	public void setExp(int exp) {
		exp = this.exp;
	}
	
	public int getExp() {
		return exp;
	}
	
	public void setHeroClass(String heroClass) {
		heroClass = this.heroClass;
	}
	
	public String getHeroClass() {
		return heroClass;
	}
}
