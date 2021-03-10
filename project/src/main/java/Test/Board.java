package Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Board {

	private int score = 0;
	private int highScore = 0;
	private int aliensPerRow = 10;
	private int alienRadius = 20;
	private Color alienColor = Color.GREEN;
	private Circle c;
	
	private int boardWidth = 600;
	private int boardHeight = 400;
	
	
	private List<Alien> alienGroup= new ArrayList<Alien>();
	Timer timer = new Timer();
	
	public void drawAliens() {
		for(Alien alien : alienGroup) {
			alien.draw();
		}
	}
	
	public void addAlien(double x, double y) {	
		Alien alien = new Alien(x,y,alienRadius,c);
		alien.setColor(alienColor);
		alienGroup.add(alien);
	}
	
	
	public static void gameOver() {
		
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		if (score > getHighScore()) {
			this.setHighScore(score);
		}
		this.score = score;
	}
	
	public int getHighScore() {
		return highScore;
	}
	
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	
	public static void main(String[] args) {
	}
	
	
}
