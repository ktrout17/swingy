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
			"DIY HEML",
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
		hero = this.hero;
		generateMap();
		generateEnemies();
		placeHero();
		artifacts.add(weapons);
		artifacts.add(helms);
		artifacts.add(armor);
	}
	
	private void generateMap() {
		int lvl = hero.getLvl();
		mapSize = (lvl - 1) * 5 + 10 - (lvl % 2);
		map = new boolean[mapSize][mapSize];
	}
	
	private void generateEnemies() {
		int rand;
		int lvl = hero.getLvl();
		
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				rand = ((int)Math.random() * 101);
				if ((lvl + 1) * 10 >= rand)
					map[i][j] = true;
			}
		}
	}
	
	public Enemies generateEnemy() {
		String enemy = enemies.get((int)Math.random() * 5);
		
		int atk = randomIntFromInterval((hero.getAtk() - 20), (hero.getAtk() + 5 + hero.getLvl()));
		int def = randomIntFromInterval((hero.getDef() - 20), (hero.getDef() + 5 + hero.getLvl()));
		int hp = randomIntFromInterval((hero.getHp() - 50), (hero.getHp() + 20 + hero.getLvl()));

		if (atk < 0)
			atk = -atk;
		
		if (def < 0)
			def = -def;
		
		if (hp < 0)
			hp = -hp;

		return new Enemies(enemy, atk, def, hp);
	}
	
	private Artifact generateArtifact() {
		String artifactName = artifacts.get((int)Math.random() * 2).get((int)Math.random() * 6);
		Artifact artifact = null;
		
		if(artifacts.get(0).contains(artifactName))
			artifact = new Weapon(artifactName, artifact.getPoints());
		else if (artifacts.get(1).contains(artifactName))
			artifact = new Helm(artifactName, artifact.getPoints());
		else 
			artifact = new Armor(artifactName, artifact.getPoints());
		return artifact;
	}
	
	public int fightRes(Character enemy) {
		int exp = enemy.getAtk() + enemy.getDef() + enemy.getHp();
		int rand = (int)Math.random() * 100;
		
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
	
	public CreateHero getHero() {
		return hero;
	}
	
	public void setHero(CreateHero hero) {
		hero = this.hero;
	}
	
	public MapPoints getHeroCoords() {
		return heroCoords;
	}
	
	public void setHeroCoords(MapPoints heroCoords) {
		heroCoords = this.heroCoords;
	}
	
	public boolean[][] getMap() {
		return map;
	}
	
	public void setMap(boolean[][] map) {
		map = this.map;
	}
}
