package ktrout.model;

import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.constraints.NotNull;

import ktrout.model.artifacts.Armor;
import ktrout.model.artifacts.Artifact;
import ktrout.model.artifacts.Helm;
import ktrout.model.artifacts.Weapon;
import ktrout.model.characters.CreateHero;
import ktrout.model.characters.Enemies;
import ktrout.model.characters.Character;
import ktrout.util.MapPoints;

public class Game {
		
	private static Game instance = null;
	
	private CreateHero hero;
	private Enemies enemy;
	private MapPoints heroCoords;
	private int mapSize;
	private boolean[][] map;
	// @NotNull
	private static ArrayList<String> enemies = new ArrayList<String>(Arrays.asList(
			"EVIL SIBLING",
			"DALEK",
			"EVIL CLERIC",
			"MAD SCIENTIST",
			"ROQUE NINJA",
			"EVIL ELF"
			));
	// @NotNull
	private static ArrayList<ArrayList<String>> artifacts = new ArrayList<ArrayList<String>>();
	
	ArrayList<String> weapons = new ArrayList<String>(Arrays.asList(
			"BRONZE DAGGER",
			"BONE DAGGER",
			"FIRELORD KEYBLADE",
			"IRON FLAIL",
			"BOW AND ARROWS",
			"WAR SCYTHE",
			"BLUNT SWORD"
			));
	
	ArrayList<String> helms = new ArrayList<String>(Arrays.asList(
			"MYRMIC HELM",
			"DIY HELM",
			"WELDING HELM",
			"SPECIAL FORCES HELM",
			"SSH-68",
			"HEDGEHOG HELM",
			"MINECRAFT HELM"
			));
	
	ArrayList<String> armor = new ArrayList<String>(Arrays.asList(
			"ENCHANTED ANGEL WINGS",
			"RED CAPE",
			"FIRELORD CHAINMAIL",
			"LEATHER GLOVES",
			"BRONZE PANTS",
			"FIRELORD BOOTS",
			"BONE SHIELD"
			));
	
	// artifacts.add(weapons);
	// artifacts.add(helms);
	// artifacts.add(armor);
	
	private Game() {
	}
	
	public static Game getInstance() {
		if (instance == null)
			instance = new Game();
		return instance;
	}
	
	public void startGame(CreateHero hero) {
		this.hero = hero;
		generateMap();
		generateEnemies();
		placeHero();
		artifacts.add(weapons);
		artifacts.add(helms);
		artifacts.add(armor);
	}
	
	private void generateMap() {
		int lvl = hero.getLvl();
		// map size formula = (level-1)*5+10-(level%2)
		mapSize = (lvl - 1) * 5 + 10 - (lvl % 2);
		map = new boolean[mapSize][mapSize];
	}
	
	private void generateEnemies() {
		int rand;
		int lvl = hero.getLvl();
		
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				rand = randomIntFromInterval(0, 101);
				if ((lvl + 1) * 10 >= rand)
					map[i][j] = true;
			}
		}
	}
	
	public Enemies generateEnemy() {
		String enemy = enemies.get(randomIntFromInterval(0, 5));
		// this.enemy.setName(enemy);
		
		int atk = randomIntFromInterval((hero.getAtk() - 20), (hero.getAtk() + 5 + hero.getLvl()));
		int def = randomIntFromInterval((hero.getDef() - 20), (hero.getDef() + 5 + hero.getLvl()));
		int hp = randomIntFromInterval((hero.getHp() - 50), (hero.getHp() + 20 + hero.getLvl()));
		Artifact artifact = generateArtifact();

		if (atk < 0)
			atk = -atk;
		
		if (def < 0)
			def = -def;
		
		if (hp < 0)
			hp = -hp;

		System.out.println("\n It appears to be a " + enemy + "!");
		return new Enemies(enemy, atk, def, hp, artifact);
	}
	
	private Artifact generateArtifact() {
		int rand = randomIntFromInterval(0, 10);
		String artifactName = artifacts.get(randomIntFromInterval(0, 2)).get(randomIntFromInterval(0, 6));
		// System.out.println("Artifact is: " + artifactName);
		Artifact artifact = null;
		
		if (rand == 1 || rand == 3 || rand == 5 || rand == 7) {
			if(artifacts.get(0).contains(artifactName)) 
				artifact = new Weapon(artifactName, randomIntFromInterval(1, 5 * (generateEnemy().getHp() + 1)));
			else if (artifacts.get(1).contains(artifactName))
				artifact = new Helm(artifactName, randomIntFromInterval(1, 3 * (generateEnemy().getHp() + 1)));
			else 
				artifact = new Armor(artifactName, randomIntFromInterval(1, 4 * (generateEnemy().getHp() + 1)));
		}
		// System.out.println("Artifact: " + artifact);
		return artifact;
	}
	
	public int fightRes(Character enemy) {
		int exp = enemy.getAtk() + enemy.getDef() + enemy.getHp();
		int rand = randomIntFromInterval(0, 100);
		
		if (rand < 3)
			return exp;
		else if (rand > 98)
			return -1;
		
		if (hero.combat(enemy))
			return exp;
		else 
			return -1;
	}
	
	private void placeHero() {
		heroCoords = new MapPoints(mapSize / 2, mapSize / 2);
		map[heroCoords.getY()][heroCoords.getX()] = false;
	}

	private int randomIntFromInterval(int min, int max) {
		return (int)Math.floor(Math.random() * (max - min + 1) + min);
	}
	
	public int getMapSize() {
		return mapSize;
	}

	public Enemies getEnemy() {
		return enemy;
	}
	
	public CreateHero getHero() {
		return hero;
	}
	
	public void setHero(CreateHero hero) {
		this.hero = hero;
	}
	
	public MapPoints getHeroCoords() {
		return heroCoords;
	}
	
	public void setHeroCoords(MapPoints heroCoords) {
		this.heroCoords = heroCoords;
	}
	
	public boolean[][] getMap() {
		return map;
	}
	
	public void setMap(boolean[][] map) {
		this.map = map;
	}
}
