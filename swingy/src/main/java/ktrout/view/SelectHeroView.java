package ktrout.view;

public interface SelectHeroView {

	void start();
	
	void updateInfo(String info);
	
	void showErrorMsg(String msg);
	
	void openGame();
	
	void openCreateHero();

	void quitGame();
}
