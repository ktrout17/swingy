package ktrout.model.characters;

public class Heroes {
    private static HeroCreator newHero(String name) {
        HeroCreator creator = new HeroCreator();
        creator.setName(name);
        creator.setLvl(0);
        creator.setExp(0);
        return creator;
    }

    public static Hero
}
