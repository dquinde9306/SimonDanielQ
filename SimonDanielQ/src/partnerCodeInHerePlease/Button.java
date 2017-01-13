package partnerCodeInHerePlease;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import gui.components.Action;
import gui.components.Components;
import simonDanielQ.ButtonInterfaceDaniel;

public class Button extends Components implements ButtonInterfaceDaniel {

	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private Action action;
	private Color c;
	private Color displayColor;
	private boolean highlight;

	public Button() {
		super(x,y,WIDTH,HEIGHT);
	}

	public boolean isHovered(int x, int y) {
		double distance = Math.sqrt(Math.pow(x-(getX()+WIDTH/2), 2)+Math.pow(y-(getY()+HEIGHT/2), 2));

		return distance < WIDTH/2;
	}

	public void act() {
		action.act();
	}



	public void setColor(Color color) {
		this.c = color;
		update();
	}

	public void highlight() {
		highlight = true;
		update();
	}

	public void dim() {
		highlight = false;
		update();
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@Override
	public void update(Graphics2D g) {
		//update button graphics(set colors when on[highlighted] & grey when off [dim])
		g = clear();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(highlight)
			g.setColor(c);
		else
			g.setColor(Color.gray);
			g.fillOval(0, 0, 50, 50);
			g.setColor(Color.BLACK);
			g.drawOval(0, 0, 49, 49);
		
	}
	private String name;
	private static int y;
	private static int x;
	public void setName(String s){
		this.name = s;
	}
	
	public String toString(){
		return name;
	}

	
	public void setX(int x) {
		this.x=x;
		
	}

	
	public void setY(int y) {
		this.y=y;
		
	}
	
}