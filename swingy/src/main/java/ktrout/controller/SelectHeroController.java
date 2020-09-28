package ktrout.controller;

import java.util.ArrayList;

import ktrout.model.Game;
import ktrout.model.characters.CreateHero;
import ktrout.util.HeroValidationException;
import ktrout.util.Database;
import ktrout.view.SelectHeroView;

public class SelectHeroController {

	private SelectHeroView view;
	private Game game;
	
	public SelectHeroController(SelectHeroView view) {
		this.view = view;
		game = Game.getInstance();
	}
	
	public void onElementSelected(int index) {
		CreateHero hero = Database.selectHeroById(index + 1);
		// System.out.println(hero);
		view.updateInfo(hero.toString());
	}
	
	public String[] getListData() {
		ArrayList<String> list = Database.selectAll();
		String[] listArray = new String[list.size()];
		listArray = list.toArray(listArray);
		return listArray;
	}
	
	public void onSelect(int index) {
		CreateHero hero;
		try {
			hero = Database.selectHeroById(index + 1);
			hero.validateHero();
		} catch (HeroValidationException e) {
			view.showErrorMsg(e.getMessage());
			return;
		}
		
		game.startGame(hero);
		view.openGame();
	}
	
	public void onCreate() {
		view.openCreateHero();
	}
}
