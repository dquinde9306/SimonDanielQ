package simonDanielQ;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gui.components.Action;
import gui.components.TextLable;
import gui.components.Visible;
import gui.screens.ClickableScreen;
import partnerCodeInHerePlease.Move;
import partnerCodeInHerePlease.Progress;
import partnerCodeInHerePlease.Button;

public class SimonScreenDaniel extends ClickableScreen implements Runnable{

	private TextLable label;
	private ButtonInterfaceDaniel[] buttons;
	private ProgressInterfaceDaniel progress;
	private ArrayList<MoveInterfaceDaniel> sequence; 
	private int roundNumber;
	private boolean acceptingInput;
	private int sequenceIndex;
	private int lastSelectedButton;




	public SimonScreenDaniel(int width, int height) {
		super(width, height);
		Thread screen = new Thread(this);
		screen.start();
	}




	@Override
	public void run(){
	    label.setText("nm");
	    nextRound();
	}



	private void nextRound() {
		acceptingInput = false;
		roundNumber++;
		progress.setRound(roundNumber);
		sequence.add(randomMove());
		progress.setSequenceSize(sequence.size());
		changeText("Simon's turn");
		label.setText("");
		playSequence();
		changeText("Your turn.");
		label.setText("");
		acceptingInput = true;
		sequenceIndex = 0;
	}




	private void changeText(String string) {
		try{
			label.setText(string);
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}		
	}




	private MoveInterfaceDaniel randomMove() {
		int select = (int) (Math.random()*buttons.length);
		while(select == lastSelectedButton){
			select = (int) (Math.random()*buttons.length);
		}
		lastSelectedButton = select;
		
		return new Move(buttons[select]);
	}



	private void addButtons(List<Visible> viewObjects) {
		int buttonCount = 3;
		Color[] colors = {Color.red, Color.blue,Color.green};
		String[] names = {"RED", "BLUE", "ORANGE", "GREEN", "YELLOW", "PURPLE"};
		buttons = new ButtonInterfaceDaniel[buttonCount];
		for(int i = 0; i < buttonCount; i++ ){
			buttons[i] = getAButton();
			buttons[i].setColor(colors[i]);
			buttons[i].setName(names[i]);
			buttons[i].setX(200*i);
			buttons[i].setY(300+i);
			final ButtonInterfaceDaniel b = buttons[i];
			System.out.println(b+" has x = "+b.getX()+", y ="+b.getY());

			b.dim();

			b.setAction(new Action(){

				public void act(){
					if(acceptingInput){
						Thread blink = new Thread(new Runnable(){

							public void run(){
								b.highlight();
								try {
									Thread.sleep(800);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								b.dim();
							}
						});
						blink.start();
						if(sequence.get(sequenceIndex).getButton() == b){
							sequenceIndex++;
						}else {
							progress.gameOver();
							return;
						}
						if(sequenceIndex == sequence.size()){
							Thread nextRound = new Thread(SimonScreenDaniel.this);
							nextRound.start();
						}
					}


				}

			});
			
			viewObjects.add(b);
			
			
		}

	}



	private ButtonInterfaceDaniel getAButton() {
		return new Button();
	}




	/**
	Placeholder until partner finishes implementation of ProgressInterface
	 */

	private ProgressInterfaceDaniel getProgress() {
		return new Progress();
	}



	private void playSequence() {
		ButtonInterfaceDaniel b = null;
		for(MoveInterfaceDaniel m: sequence){
			if(b!=null)b.dim();
			b = m.getButton();
			b.highlight();
			try {
				Thread.sleep((long)(2000*(2.0/(roundNumber+2))));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		b.dim();
	}




	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		addButtons(viewObjects);
		progress = getProgress();
		label = new TextLable(130,230,300,40,"Let's play Simon!");
		sequence = new ArrayList<MoveInterfaceDaniel>();
		//add 2 moves to start
		lastSelectedButton = -1;
		sequence.add(randomMove());
		sequence.add(randomMove());
		roundNumber = 0;
		viewObjects.add(progress);
		viewObjects.add(label);

		
	}



















}