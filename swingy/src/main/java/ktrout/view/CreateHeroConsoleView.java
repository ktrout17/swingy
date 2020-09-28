package ktrout.view;

import java.util.Scanner;

import javax.validation.constraints.NotNull;

import ktrout.Main;
import ktrout.controller.CreateHeroController;
import ktrout.view.GameConsoleView;

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
		
		System.out.println("\nTo create your hero, enter a name and choose a class.");
		System.out.println("What is your name?");
		@NotNull(message = "Name cannot be null")
		String name = scanner.nextLine();
		System.out.println("\nClasses: 	ATTACK		DEFENSE		HP\n" +
				"FIGHTER		50		50		150\n" + 
				"NINJA		40		20		100\n" +
				"WIZARD		35		20		80\n" + 
				"ARCHER		45		40		80\n" +
				"BESERKER	50		30		120\n" +
				"SHADOW KNIGHT	30		20		100\n" +
				"\nChoose a class: ");
		String heroClass = scanner.nextLine();
		
		System.out.println("\nYou've chosen to be a " + heroClass.toUpperCase() + " and your name is " + name.toUpperCase() + ".");
		System.out.println("\nFINALIZE - finalize your hero creation.");
		System.out.println("\nAvailable commands:");
		System.out.println("FINALIZE");
		System.out.println("_______________________________________\n");
		while (scanner.hasNext()) {
			String input = scanner.nextLine();
			if ("finalize".equalsIgnoreCase(input)) {
				controller.onCreate(name, heroClass.toUpperCase());
				break;
			} else {
				System.out.println("Unknown command.");
				System.out.println("Available commands:");
				System.out.println("FINALIZE");
				System.out.println("_______________________________________\n");
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
