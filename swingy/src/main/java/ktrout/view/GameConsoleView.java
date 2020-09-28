package ktrout.view;

import java.util.Scanner;

import ktrout.Main;
import ktrout.controller.GameController;
import ktrout.model.Game;
import ktrout.model.characters.Enemies;
import ktrout.util.MapPoints;

public class GameConsoleView implements GameView {

	private GameController controller;
	// private Game game;
	
	@Override
	public void start() {
		controller = new GameController(this);
		controller.onStart();
	}
	
	@Override
	public void update(Game game) {
		System.out.println("\n**************** YOUR INFO ****************\n");
		System.out.println(game.getHero().toString() + 
				"Position:	" + "(" + game.getHeroCoords().getX() +
				"," + game.getHeroCoords().getY() + ")");
		System.out.println("\n*******************************************\n");
		
		getUserInput();
	}
	
	private void getUserInput() {
		Scanner scanner = Main.getScanner();
		
		System.out.println("Where would you like to move?");
		System.out.println("\nThese are your choices:");
		System.out.println("NORTH, SOUTH, EAST, WEST");
		// System.out.println("\nIf you would like to switch to GUI:\nSWITCH");
		System.out.println("\nAvailable commands: ");
		System.out.println("NORTH, SOUTH, EAST, WEST");
		System.out.println("_______________________________________\n");
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
			// } else if ("switch".equalsIgnoreCase(input)) {
			// 	controller.onSwitch();
			// 	break;
			} else {
				System.out.println("Unknown command.");
				System.out.println("Please choose one of the vailable commands: ");
				System.out.println("NORTH, SOUTH, EAST, WEST");
				System.out.println("_______________________________________\n");
			}
		}
	}
	
	@Override
	public void printMap(boolean[][] map, MapPoints heroCoords) {
		System.out.printf("\nMAP %dx%d", map.length, map.length);
		System.out.println("\n");
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (heroCoords.getX() == j && heroCoords.getY() == i)
					System.out.print("H ");
				else if (map[i][j])
					System.out.print("* ");
				else
					System.out.print(". ");
			}
			System.out.println();
		}
	}
	
	@Override
	public void gameDone() {
		System.out.println("\nSee you next time!");
		Main.getFrame().dispose();
		Main.closeConnections();
	}
	
	@Override
	public void showMsg(String msg) {
		System.out.println(msg);
	}
	
	@Override
	public void getCombatInput() {
		// System.out.println("Enemy is: " + enemy.getName());
		Scanner scanner = Main.getScanner();
		
		System.out.println();
		System.out.println("Something has blocked your path!");
		System.out.println("\nWhat would you like to do?");
		System.out.println("\nFIGHT - enter combat with the enemy");
		System.out.println("RUN - you will have a 50% chance to move back to your previous position.");
		System.out.println("\nAvailable commands:");
		System.out.println("FIGHT, RUN");
		System.out.println("_______________________________________\n");
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
				System.out.println("_______________________________________\n");
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
		System.out.println("\nAvailable commands:");
		System.out.println("DROP, REPLACE");
		System.out.println("_______________________________________\n");
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
				System.out.println("_______________________________________\n");
			}
		}
		return false;
	}
	
	@Override
	public void switchView() {
		new GameGuiView().start();
	}
}
