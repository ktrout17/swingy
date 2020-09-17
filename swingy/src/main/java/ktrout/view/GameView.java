package ktrout.view;

import ktrout.model.Game;
import ktrout.util.MapPoints;

public interface GameView {

	
	void start();
	
	void printMap(boolean[][] map, MapPoints heroCoords);
	
	void update(Game game);
	
	void gameDone();
	
	void showMsg(String msg);
	
	void getEnemyCollInput();
	
	boolean replaceArtifact(String replaceMsg);
	
	void switchView();
}
