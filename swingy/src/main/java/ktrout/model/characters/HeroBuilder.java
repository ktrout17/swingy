package ktrout.model.characters;

public class HeroBuilder {
	
	private static HeroCreator createNew(String name) {
		HeroCreator creator = new HeroCreator();
		creator.setName(name);
		creator.setLvl(0);
		creator.setExp(0);
		return creator;
	}
	
	public static CreateHero createFighter(String name) {
		HeroCreator creator = createNew(name);
		creator.setAtk(50);
		creator.setDef(50);
		creator.setHp(150);
		creator.setHeroClass("FIGHTER");
		return creator.getHero();
	}
	
	public static CreateHero createNinja(String name) {
		HeroCreator creator = createNew(name);
		creator.setAtk(40);
		creator.setDef(20);
		creator.setHp(100);
		creator.setHeroClass("NINJA");
		return creator.getHero();
	}
	
	public static CreateHero createWizard(String name) {
		HeroCreator creator = createNew(name);
		creator.setAtk(35);
		creator.setDef(20);
		creator.setHp(80);
		creator.setHeroClass("WIZARD");
		return creator.getHero();
	}
	
	public static CreateHero createArcher(String name) {
		HeroCreator creator = createNew(name);
		creator.setAtk(45);
		creator.setDef(40);
		creator.setHp(80);
		creator.setHeroClass("ARCHER");
		return creator.getHero();
	}
	
	public static CreateHero createBeserker(String name) {
		HeroCreator creator = createNew(name);
		creator.setAtk(60);
		creator.setDef(30);
		creator.setHp(120);
		creator.setHeroClass("BESERKER");
		return creator.getHero();
	}
	
	public static CreateHero createShadowKnight(String name) {
		HeroCreator creator = createNew(name);
		creator.setAtk(30);
		creator.setDef(20);
		creator.setHp(100);
		creator.setHeroClass("SHADOW KNIGHT");
		return creator.getHero();
	}
}
