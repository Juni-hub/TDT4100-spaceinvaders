package spaceInvaders;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Shot {
	
	private double posx;
	private double posy;
	private Color shotColor = Color.BLACK;
	private double shotRadius = 5;
	private Boolean hit;
	private Circle c;
	
	public Shot(double posx, Circle c) {
		this.posx = posx+300;
		this.posy = 350;
		this.hit = false;
		this.c = c;
	}
	
	public void setC(Circle c) {
		this.c = c;
	}
	
	public Circle getC() {
		return c;
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
		
	public Color getShotColor() {
		return shotColor;
	}
	
	public double getShotRadius() {
		return shotRadius;
	}
	
	public Boolean getHit() {
		return hit;
	}
	
	public Boolean hitsAlien(double a_x, double a_y, double radius) {
		double dist = Math.pow(a_x-this.posx, 2) + Math.pow(a_y-this.posy, 2);
		if(dist < (radius + this.shotRadius)*(radius + this.shotRadius)) {
			hit = true;
			System.out.println(dist);
			return true;
		}
		return false;
	}
}

