package partnerCodeInHerePlease;

import simonDanielQ.ProgressInterfaceDaniel;

public class Progress extends Component implements ProgressInterfaceDaniel {
	private static final int WIDTH = 120;
	private static final int HEIGHT = 50;
	
	private boolean gameOver;
	private int round;
	private int sequence;
	
	public Progress() {
		super(60,60,WIDTH,HEIGHT);
	}

	@Override
	public void gameOver() {
		gameOver=false;
		update();

	}

	@Override
	public void setRound(int roundNumber) {
		round +=roundNumber;
		update();

	}

	@Override
	public void setSequenceSize(int size) {
		sequence+=size;
		update();

	}

}
