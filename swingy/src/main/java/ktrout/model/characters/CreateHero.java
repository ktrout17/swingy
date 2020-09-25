package ktrout.model.characters;

import java.util.logging.Level;
import java.util.Set;

import javax.validation.ValidatorFactory;
import javax.validation.Validation;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.Validator;

import ktrout.model.artifacts.Armor;
import ktrout.model.artifacts.Helm;
import ktrout.model.artifacts.Weapon;
import ktrout.util.HeroValidationException;

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
			this.hp = this.hp - this.helm.getPoints();
			if (this.hp + helm.getPoints() <= 0) {
				this.hp = this.hp + this.helm.getPoints();
				return;
			}
		}
		this.hp = this.hp + helm.getPoints();
		helm = this.helm;
	}
	
	public void equipArmor(Armor armor) {
		if (this.armor != null)
			this.def = this.def - this.armor.getPoints();
		this.def = this.def + this.armor.getPoints();
		armor = this.armor;
	}
	
	public void equipWeap(Weapon weap) {
		if (this.weap != null)
			this.atk = this.atk - this.weap.getPoints();
		this.atk = this.atk + this.weap.getPoints();
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

	public void validateHero() throws HeroValidationException {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<CreateHero>> constraintViolations = validator.validate(this);
		if (constraintViolations.size() != 0) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Hero validation ERROR/S: ");
			stringBuilder.append(constraintViolations.size() + "\n");
			for (ConstraintViolation<CreateHero> constViol : constraintViolations) {
				stringBuilder.append("property: [");
				stringBuilder.append(constViol.getPropertyPath());
				stringBuilder.append("], value: [");
				stringBuilder.append(constViol.getInvalidValue());
				stringBuilder.append("], message: [");
				stringBuilder.append(constViol.getMessage() + "]\n");
			}
			throw new HeroValidationException(stringBuilder.toString());
		}

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
