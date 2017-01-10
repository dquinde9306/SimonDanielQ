package simonDanielQ;

import gui.GUIApplication;

public class SimonGameDaniel extends GUIApplication {

	public SimonGameDaniel(int width, int height) {
		super(width, height);
	}

	@Override
	public void initScreen() {
		SimonScreenDaniel screen = new SimonScreenDaniel(getWidth(),getHeight());
		setScreen(screen);

	}

	public static void main(String[] args) {
		SimonGameDaniel simonGame = new SimonGameDaniel(800,500);
		Thread game = new Thread(simonGame);
		game.start();

	}

}
