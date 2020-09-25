package ktrout.view;

import java.util.Scanner;

import ktrout.Main;
import ktrout.controller.GameController;
import ktrout.model.Game;
import ktrout.util.MapPoints;

public class GameConsoleView implements GameView {

	private GameController controller;
	
	@Override
	public void start() {
		controller = new GameController(this);
		controller.onStart();
	}
	
	@Override
	public void update(Game game) {
		System.out.println("**********YOUR INFO**********");
		System.out.println(game.getHero().toString() + 
				"Position: " + "(" + game.getHeroCoords().getX() +
				"," + game.getHeroCoords().getY() + ")");
		System.out.println("*****************************");
		
		getUserInput();
	}
	
	private void getUserInput() {
		Scanner scanner = Main.getScanner();
		
		System.out.println("Where would you like to move?");
		System.out.println("These are your choices:");
		System.out.println("NORTH, SOUTH, EAST, WEST");
		System.out.println("If you would like to switch to GUI:\nSWITCH");
		System.out.println("Available commands: ");
		System.out.println("NORTH, SOUTH, EAST, WEST, SWITCH");
		while (scanner.hasNext()) {
			String input = scanner.nextLine();
			
			if ("map".equalsIgnoreCase(input)) {
				controller.onPrintMap();
				break;
			} else if ("north".equalsIgnoreCase(input) ||
					"south".equalsIgnoreCase(input) ||
					"east".equalsIgnoreCase(input) ||
					"west".equalsIgnoreCase(input)) {
				controller.onMove(input);
				break;
			} else if ("switch".equalsIgnoreCase(input)) {
				controller.onSwitch();
				break;
			} else {
				System.out.println("Unknown command.");
				System.out.println("Please choose one of the vailable commands: ");
				System.out.println("NORTH, SOUTH, EAST, WEST, SWITCH");
			}
		}
	}
	
	@Override
	public void printMap(boolean[][] map, MapPoints heroCoords) {
		System.out.printf("MAP %dx%d", map.length, map.length);
		System.out.println();
		for (int i = 0; i < map[i].length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (heroCoords.getX() == j && heroCoords.getY() == i)
					System.out.println("H ");
				else if (map[i][j])
					System.out.println("* ");
				else
					System.out.println(". ");
			}
			System.out.println();
		}
	}
	
	@Override
	public void gameDone() {
		System.out.println("Good bye.");
		Main.getFrame().dispose();
		Main.closeConnections();
	}
	
	@Override
	public void showMsg(String msg) {
		System.out.println(msg);
	}
	
	@Override
	public void getEnemyCollInput() {
		Scanner scanner = Main.getScanner();
		
		System.out.println();
		System.out.println("You've encountered an enemy.");
		System.out.println("What would you like to do?");
		System.out.println("FIGHT - enter combat with the enemy");
		System.out.println("RUN - you will have a 50% chance to move back to your previous position.");
		System.out.println("Available commands:");
		System.out.println("FIGHT, RUN");
		while (scanner.hasNext()) {
			String input = scanner.nextLine();
			
			if ("fight".equalsIgnoreCase(input)) {
				controller.onFight();
				break;
			} else if ("run".equalsIgnoreCase(input)) {
				controller.onRun();
				break;
			} else {
				System.out.println("Unknown command.");
				System.out.println("Please choose one of the vailable commands: ");
				System.out.println("FIGHT, RUN");
			}
		}
	}
	
	@Override
	public boolean replaceArtifact(String replaceMsg) {
		Scanner scanner = Main.getScanner();
		
		System.out.println();
		System.out.println("Would you like to replace your current " + replaceMsg + "?");
		System.out.println("DROP - drop current artifact");
		System.out.println("REPLACE - replace current artifact");
		System.out.println("Available commands:");
		System.out.println("DROP, REPLACE");
		while (scanner.hasNext()) {
			String input = scanner.nextLine();
			
			if ("drop".equalsIgnoreCase(input)) {
				return false;
			} else if ("replace".equalsIgnoreCase(input)) {
				return true;
			} else {
				System.out.println("Unknown command.");
				System.out.println("Please choose one of the vailable commands: ");
				System.out.println("DROP, REPLACE");
			}
		}
		return false;
	}
	
	@Override
	public void switchView() {
		new GameGUIView().start();
	}
}
