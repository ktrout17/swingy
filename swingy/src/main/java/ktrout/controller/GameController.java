package ktrout.controller;

import java.util.Random;

import ktrout.model.Game;
import ktrout.model.artifacts.Armor;
import ktrout.model.artifacts.Artifact;
import ktrout.model.artifacts.Helm;
import ktrout.model.artifacts.Weapon;
import ktrout.model.characters.CreateHero;
import ktrout.model.characters.Enemies;
import ktrout.util.MapPoints;
import ktrout.util.Database;
import ktrout.view.GameView;

public class GameController {

	private GameView view;
	private Game game;
	private MapPoints prevPos;
	
	public GameController(GameView view) {
		this.view = view;
		game = Game.getInstance();
		prevPos = new MapPoints(0, 0);
	}
	
	public void onStart() {
		view.update(game);
	}
	
	public void onPrintMap() {
		view.printMap(game.getMap(), game.getHeroCoords());
		view.update(game);
	}
	
	public void onMove(String direction) {
		int x = game.getHeroCoords().getX();
		int y = game.getHeroCoords().getY();
		prevPos.setX(x);
		prevPos.setY(y);
	
		switch (direction.toUpperCase()) {
		case "NORTH":
			y--;
			break;
		case "SOUTH":
			y++;
			break;
		case "EAST":
			x++;
			break;
		case "WEST":
			x--;
			break;
	}
	
	if (x < 0 || y < 0 || x >= game.getMapSize() || y >= game.getMapSize()) {
		winGame();
		return;
	}
	
	game.getHeroCoords().setX(x);
	game.getHeroCoords().setY(y);
	
	if (game.getMap()[y][x])
		enemyCollision();
	
	if (game.getHero().getHp() > 0)
		view.update(game);
	}
	
	private void winGame() {
		view.showMsg("\nHey, you reached the end of the map and won this round! You get additional " + game.getMapSize() * 100 + " exp!");
		addExp(game.getMapSize() * 100);
		updateDb();
		view.gameDone();
	}
	
	private void updateDb() {
		CreateHero hero = game.getHero();
		Database.updateHero(hero);
	}

	private void enemyCollision() {
		view.getCombatInput();
	}
	
	public void onRun() {
		if (new Random().nextBoolean()) {
			view.showMsg("\nLucky you! You got away and moved back to your previous position.");
			game.getHeroCoords().setX(prevPos.getX());
			game.getHeroCoords().setY(prevPos.getY());
		} else {
			view.showMsg("No running, time to fight - good luck!");
			onFight();
		}
	}
	
	private void setArtifact(Artifact artifact) {
		if (artifact != null) {
			if (artifact instanceof Weapon) {
				if (game.getHero().getWeap() == null || view.replaceArtifact("Your weapon: " + game.getHero().getWeap() + ", found: " + artifact)) {
					game.getHero().equipWeap((Weapon) artifact);
					view.showMsg("You equipped a new weapon: " + artifact);
				}
			} else if (artifact instanceof Helm) {
				if (game.getHero().getHelm() == null || view.replaceArtifact("Your Helm: " + game.getHero().getHelm() + ", found: " + artifact)) {
					game.getHero().equipHelm((Helm) artifact);
					view.showMsg("You equipped a new helm: " + artifact);
				}
			} else if (artifact instanceof Armor) {
				if (game.getHero().getArmor() == null || view.replaceArtifact("Your armor: " + game.getHero().getArmor() + ", found: " + artifact)) {
					game.getHero().equipArmor((Armor) artifact);
					view.showMsg("You eqipped new armor: " + artifact);
				}
			}
		}
	}
	
	public void onFight() {
		Enemies enemy = game.generateEnemy();
		int exp = game.fightRes(enemy);
		String enemyName = game.generateEnemy().getName();
		view.showMsg("\nIt's a " + enemyName + "!");

		if (exp >= 0) {
			view.showMsg("\nYou defeated the "+ enemyName + "! You gained " + exp + " exp.");
			addExp(exp);
			game.getMap()[game.getHeroCoords().getY()][game.getHeroCoords().getX()] = false;
			setArtifact(enemy.getArtifact());
		} else {
			view.showMsg("\nThe " + enemyName + " was too strong!");
			view.showMsg("You died - Game Over.");
			view.gameDone();
		}
	}
	
	private void addExp(int addExp) {
		int lvl = game.getHero().getLvl();
		game.getHero().addExp(addExp);
		if (lvl != game.getHero().getLvl()) 
			view.showMsg("\nYou Leveled UP! You are now level " + game.getHero().getLvl() + ".\nATTACK, DEFENSE and HP have increased.");
	}
	
	public void onSwitch() {
		view.switchView();
	}

	public void onQuit() {
		updateDb();
		view.quitGame();
	}
	
}
