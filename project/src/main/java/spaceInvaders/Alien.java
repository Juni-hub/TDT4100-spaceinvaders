package spaceInvaders;

import javafx.scene.paint.Color;

public class Alien {
	
	private double posx;
	private double posy;
	private double radius;
	private Color alienColor = Color.GREEN;
	
	public Alien(double posx,double posy, double radius) {
		this.posx = posx;
		this.posy = posy;
		this.radius = radius;
	}

	public double getPosx() {
		return posx;
	}

	public double getPosy() {
		return posy;
	}
	
	public Color getAlienColor() {
		return alienColor;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public void setPosx(double posx) {
		this.posx = posx;
	}
	
	public void setPosy(double posy) {
		this.posy = posy;
	}
	
}
