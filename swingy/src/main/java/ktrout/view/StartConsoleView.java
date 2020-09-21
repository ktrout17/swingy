package ktrout.view;

import java.util.Scanner;

import ktrout.Main;
import ktrout.controller.StartController;

public class StartConsoleView implements StartView {

	private StartController controller;
	
	@Override
	public void start() {
		controller = new StartController(this);
		System.out.println("Welcome to Swingy - console view");
		System.out.println("Let's begin.");
		
		Scanner scanner = Main.getScanner();
		System.out.println();
		System.out.println("CREATE - create a new hero");
		System.out.println("SELECT - select a previously created hero");
		System.out.println("SWITCH - switch to GUI view");
		System.out.println("Available Commands:");
		System.out.println("CREATE, SELECT, SWITCH");
		while (scanner.hasNext()) {
			String input = scanner.nextLine();
			if ("create".equalsIgnoreCase(input)) {
				controller.onCreate();
				break;
			} else if ("select".equalsIgnoreCase(input)) {
				controller.onSelect();
				break;
			} else if ("switch".equalsIgnoreCase(input)) {
				controller.onSwitch();
				break;
			} else {
				System.out.println("Unknown Command.");
				System.out.println("Available Commands:");
				System.out.println("CREATE, SELECT, SWITCH");
			}
		}
	}
	
	@Override
	public void openCreateHero() {
		new CreateHeroConsoleView().start();
	}
	
	@Override
	public void switchView() {
		new StartGuiView().start();
	}
	
	@Override
	public void openSelectHero() {
		new SelectHeroConsoleView().start();
	}
}