package spaceInvaders;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Shot {
	
	private double posx;
	private double posy;
	private Color shotColor = Color.BLACK;
	private double shotRadius = 10;
	private Boolean hit;
	
	public Shot(double posx) {
		this.posx = posx+300;
		this.posy = 350;
		this.hit = false;
	}

		
	public void moveShot(Board board) {
		/*for (Alien alien: board.getAlienGroup()) {
			if(alien.getPosx()-this.getPosx() <0 && alien.getPosy() == this.getPosy() && alien.getAlive() == true)
				alien.setAlive(false);
				this.hit = true;
		}*/
		this.setPosy (this.getPosy()-50);
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
}

