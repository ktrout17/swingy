package ktrout.view;

import java.util.Scanner;

import ktrout.Main;
import ktrout.controller.CreateHeroController;

public class CreateHeroConsoleView implements CreateHeroView {

	private CreateHeroController controller;
	
	@Override
	public void start() {
		controller = new CreateHeroController(this);
		getUserInput();
	}
	
	@Override
	public void getUserInput() {
		Scanner scanner = Main.getScanner();
		
		System.out.println("To create your hero, enter a name and choose a class.");
		System.out.println("What is your name?");
		String name = scanner.nextLine();
		System.out.println("Classes: ATTACK	DEFENSE	HP\n" +
				"FIGHTER		50		50		150\n" + 
				"NINJA			40		20		100\n" +
				"WIZARD			35		20		80\n" + 
				"ARCHER			45		40		80\n" +
				"BESERKER		50		30		120\n" +
				"SHADOWKNIGHT	30		20		100\n" +
				"Choose a class: ");
		String heroClass = scanner.nextLine();
		
		System.out.println("CREATE - finalize your hero creation.");
		System.out.println("Available commands:");
		System.out.println("CREATE");
		while (scanner.hasNext()) {
			String input = scanner.nextLine();
			if ("create".equalsIgnoreCase(input)) {
				controller.onCreate(name, heroClass);
				break;
			} else {
				System.out.println("Unknown command.");
				System.out.println("Available commands:");
				System.out.println("CREATE");
			}
		}
	}
	
	@Override
	public void showErrorMsg(String msg) {
		System.out.print("Error: " + msg);
	}
	
	@Override
	public void openGame() {
		new GameConsoleView().start();
	}
}
