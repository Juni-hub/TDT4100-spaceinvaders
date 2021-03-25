package spaceInvaders;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
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
	private Player player;
	
	public Board(Player player) {
		this.player = player;
	}
	
	public void startGame() {
		AnimationTimer timer = new AnimationTimer() {
		private long lastUpdate; //forrige gang handle ble kjørt
		
		private double speed = 50; //antall piksler
		
		@Override
		public void start() {
			lastUpdate = System.nanoTime();
			super.start();
		}
		
		@Override
		public void handle(long now) {
			drawAliens();
			long elapsedNanoSeconds = now - lastUpdate;
			double elapsedSeconds = elapsedNanoSeconds/1000000000;
			
			lastUpdate = now;
			}
		};	
	}
	private List<Alien> alienGroup= new ArrayList<Alien>();
	
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
