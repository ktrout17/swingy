package ktrout.controller;

import javax.validation.ValidationException;

import ktrout.model.Game;
import ktrout.model.characters.CreateHero;
import ktrout.model.characters.HeroFactory;
import ktrout.view.CreateHeroView;
import ktrout.util.Database;
import ktrout.util.HeroValidationException;

public class CreateHeroController {
	
	private CreateHeroView view;
	private Game game;
	
	public CreateHeroController(CreateHeroView view) {
		view = this.view;
		game = Game.getInstance();
	}
	
	public void onCreate(String name, String heroClass) {
		CreateHero hero;
		try {
			hero = HeroFactory.newHero(name, heroClass);
			hero.validateHero();
		} catch (IllegalArgumentException | HeroValidationException e) {
			view.showErrorMsg(e.getMessage());
			view.getUserInput();
			return;
		}
		
		int id = Database.insert(hero.getName(), hero.getHeroClass(), hero.getLvl(), hero.getExp(), hero.getAtk(), hero.getDef(), hero.getHp());
		hero.setId(id);
		game.startGame(hero);
		view.openGame();
	}
}
