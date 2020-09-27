package ktrout.model.characters;

public abstract class HeroFactory {
	public static CreateHero newHero(String name, String heroClass) {
		switch (heroClass.toUpperCase()) {
			case "FIGHTER":
				return HeroBuilder.createFighter(name);
			case "NINJA":
				return HeroBuilder.createNinja(name);
			case "WIZARD":
				return HeroBuilder.createWizard(name);
			case "ARCHER":
				return HeroBuilder.createArcher(name);
			case "BESERKER":
				return HeroBuilder.createBeserker(name);
			case "SHADOW KNIGHT":
				return HeroBuilder.createShadowKnight(name);
			default:
				throw new IllegalArgumentException("Invalid hero class: " + heroClass);
		}
	}
}
