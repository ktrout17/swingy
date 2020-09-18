package ktrout.controller;

import ktrout.view.StartView;

public class StartController {
	
	private StartView view;
	
	public StartController(StartView view) {
		view = this.view;
	}
	
	public void onCreate() {
		view.openCreateHero();
	}
	
	public void onSwitch() {
		view.switchView();
	}
	
	public void onSelect() {
		view.openSelectHero();
	}
}
