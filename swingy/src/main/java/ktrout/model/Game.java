package ktrout.model;

import ktrout.model.characters.CreateHero;
import ktrout.model.characters.Enemy;
import ktrout.model.characters.EnemyFactory;

public class Game {
		
	private static Game instance = null;
	
	private CreateHero hero;
	private int mapSize;
	private boolean[][] map;
	
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
	
	public Enemy generateEnemy() {
		int id = ((int)Math.random() * 5);
		EnemyFactory.newEnemy(id);
		return new getEnemy();
	}
}
