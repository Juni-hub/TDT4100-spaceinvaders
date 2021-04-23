package spaceInvaders;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.lang.Math;

public class Shot {
	
	private double posx;
	private float posy;
	private Color shotColor = Color.BLACK;
	private double shotRadius = 5;
	private Circle c;
	private int shotSpeed = 1;
	private Board board;

	
	public Shot(double posx, Circle c, Board board) {
		this.posx = posx+300;
		this.posy = 350;
		this.c = c;
		this.board = board;
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

	public float getPosy() {
		return posy;
	}
		
	public Color getShotColor() {
		return shotColor;
	}
	
	public double getShotRadius() {
		return shotRadius;
	}
	
	public Alien hitsAlien() {
		if(board.getAlienGroup().size() != 0) {
			for (Alien alien : board.getAlienGroup()) {
				if((Math.abs(alien.getPosx()-this.posx) < 15) && this.posy-alien.getPosy()<15) {
					if(alien.getAlive() == true) {
					board.removeShot(this);
					alien.setDead();
					board.setScore(10);
					return alien;
					}
				}
			}
		}
		return null;
	}
	
	public void moveShot() {
		this.posy -= shotSpeed;
		if (this.posy < -10) {
			board.removeShot(this);
		}
	}
	
	public int getShotSpeed() {
		return shotSpeed;
	}
}

