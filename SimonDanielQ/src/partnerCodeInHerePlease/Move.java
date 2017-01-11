package partnerCodeInHerePlease;

import simonDanielQ.ButtonInterfaceDaniel;
import simonDanielQ.MoveInterfaceDaniel;

public class Move implements MoveInterfaceDaniel {

	private ButtonInterfaceDaniel b; 
	
	public Move(ButtonInterfaceDaniel b) {
		this.b = b;
	}

	public ButtonInterfaceDaniel getButton() {
		return b;
	}

}