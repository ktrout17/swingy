package ktrout.view;

import java.util.Scanner;

import ktrout.Main;
import ktrout.controller.SelectHeroController;
import ktrout.view.CreateHeroConsoleView;
import ktrout.view.GameConsoleView;
import ktrout.view.StartConsoleView;

public class SelectHeroConsoleView implements SelectHeroView {
	
	private SelectHeroController controller;
	private int lastIndex = -1;
	
	@Override
	public void start() {
		controller = new SelectHeroController(this);
		
		getInput();
	}
	
	private void getInput() {
		Scanner scanner = Main.getScanner();
		System.out.println("\nAvailable Heroes: ");
		printHeroes(controller.getListData());
		System.out.println("_______________________________________");
			
		System.out.println();
		System.out.println("CREATE - create a new hero");
		System.out.println("NUMBER - enter the number of previously created heroes to see more info.");
		System.out.println("SELECT - to select hero after choosing previously created hero.");
		System.out.println("QUIT - exit the game");
		System.out.println("\nAvailable Commands:");
		System.out.println("CREATE, NUMBER, SELECT, QUIT");
		System.out.println("_______________________________________\n");
		while (scanner.hasNext()) {
			String input = scanner.nextLine();
			if ("create".equalsIgnoreCase(input)) {
				controller.onCreate();
				break;
			} else if (isValNumStr(input, controller.getListData().length)) {
				lastIndex = Integer.parseInt(input) - 1;
				controller.onElementSelected(lastIndex);
				System.out.println("SELECT - to select this hero.\n");
				System.out.println("CREATE - to create a new hero.\n");
				System.out.println("QUIT - exit the game.\n");
			} else if ("select".equalsIgnoreCase(input) && lastIndex != -1) {
				controller.onSelect(lastIndex);
				break;
			} else if("quit".equalsIgnoreCase(input)) {
				controller.onQuit();
				break;
			} else {
				System.out.println("Unknown Command.");
				System.out.println("Available Commands:");
				System.out.println("CREATE, NUMBER, SELECT, QUIT");
				System.out.println("_______________________________________\n");
			}
		}
	}
	
	private boolean isValNumStr(String str, int max) {
		try {
			int n = Integer.parseInt(str);
			if (n <= 0 || n > max)
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	private void printHeroes(String[] heroes) {
		if (heroes.length == 0) {
			System.out.println("No previously saved heroes.");
			new StartConsoleView().start();
		}
		for (String hero : heroes) {
			System.out.println(hero.toUpperCase());
		}
	}
	
	@Override 
	public void updateInfo(String info) {
		System.out.println("\n**************** HERO'S INFO ****************\n");
		System.out.println(info);
		System.out.println("*******************************************\n");
	}
	
	@Override
	public void showErrorMsg(String msg) {
		System.out.println("Error: " + msg);
		getInput();
	}
	
	@Override
	public void openGame() {
		new GameConsoleView().start();
	}
	
	@Override
	public void openCreateHero() {
		new CreateHeroConsoleView().start();
	}

	@Override
	public void quitGame() {
		System.out.println("\nGoodbye!");
		Main.getFrame().dispose();
		Main.closeConnections();
	}
}
