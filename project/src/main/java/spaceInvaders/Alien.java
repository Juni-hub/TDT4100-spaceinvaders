package spaceInvaders;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Alien {
	
	private boolean alive = true;
	private double posx;
	private double posy;
	private double radius;
	private Circle c;
	
	public Alien(double posx,double posy, double radius, Circle c) {
		this.posx = posx;
		this.posy = posy;
		this.radius = radius;
		this.c = c;
	}
	
	public void dead() {
		this.alive = false;
	}
	
	public void skyt() {
		Shot shot = new Shot(this.posx, this.posy);
		shot.shoot();
	}

	public boolean isAlive() {
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
	
	public void setColor(Color color) {
		c.setFill(color);
	}
	
	public void draw() {
		c.setRadius(radius);
		c.setTranslateX(posx);
		c.setTranslateY(posy);
	}
}
