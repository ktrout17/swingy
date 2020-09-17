package ktrout.controller;

import ktrout.model.Game;
import ktrout.model.characters.CreateHero;
import ktrout.model.characters.HeroFactory;
import ktrout.view.CreateHeroView;

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
		}
	}
}
