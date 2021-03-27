package spaceInvaders;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import spaceInvaders.Board;

public class Alien {
	
	private boolean alive = true;
	private double posx;
	private double posy;
	private double radius;
	private Circle c = new Circle();
	private Color alienColor = Color.GREEN;
	
	public Alien(double posx,double posy, double radius) {
		this.posx = posx;
		this.posy = posy;
		this.radius = radius;
	}
	
	public void dead() {
		this.alive = false;
	}

	public boolean getAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public double getPosx() {
		return posx;
	}

	public void setPosx(double posx) {
		this.posx = posx;
	}

	public double getPosy() {
		return posy;
	}

	public void setPosy(double posy) {
		this.posy = posy;
	}
	
	
	public Color getAlienColor() {
		return alienColor;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public Circle getC() {
		return c;
	}
	
	
	
}
