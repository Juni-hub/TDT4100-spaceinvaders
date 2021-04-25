package spaceInvaders;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import spaceInvaders.Board;

public class Alien {
	
	private double posx;
	private double posy;
	private double radius;
	//private Circle c = new Circle();
	private Color alienColor;
	
	public Alien(double posx,double posy, double radius, Circle c, Board board) {
		this.posx = posx;
		this.posy = posy;
		this.radius = radius;
		//this.c = c;
		this.alienColor = Color.GREEN;
	}

	public double getPosx() {
		return posx;
	}

	public void setPosx(double posx) {
		this.posx = posx;
		//this.c.setCenterX(posx);
	}

	public double getPosy() {
		return posy;
	}

	public void setPosy(double posy) {
		this.posy = posy;
		//this.c.setCenterY(posy);
	}
	
	
	public Color getAlienColor() {
		return alienColor;
	}
	
	public double getRadius() {
		return radius;
	}
	
	/*
	public Circle getC() {
		return c;
	}
	
	public void setC(Circle c) {
		this.c = c;
	}
	*/
}
