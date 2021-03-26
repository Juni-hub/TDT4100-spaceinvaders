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
	private int boardWidth = 600;
	private int boardHeight = 400;
	private Player player;
	private List<Alien> alienGroup= new ArrayList<Alien>();
	private Boolean endGame;
	
	public Board(Player player) {
		this.player = player;
		this.endGame = false;
	}
	
	public void startGame() {
	}
	
	public void drawAlienRow() {
		for(int i = 0; i<aliensPerRow;i++) {
			Alien alien = new Alien(i*60+30,30);
			alienGroup.add(alien);
			
		}
	}
	
	public void pushAliensDown() {
		for (int i = 0; i <alienGroup.size();i++) {
			if (alienGroup.get(i).getPosy() == 30) {
				alienGroup.get(i).setPosy(alienGroup.get(i).getPosy()+30);
			} else {
			alienGroup.get(i).setPosy(alienGroup.get(i).getPosy()+60);
			}
		}
		
		for (int i=0; i<aliensPerRow;i++) {
			if (alienGroup.get(i).getPosy() == 420) {
				gameOver();
			}
		}
	}
	
	
	public void gameOver() {
		this.endGame = true;
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
	
	public List<Alien> getAlienGroup() {
		return alienGroup;
	}
	
	public int getAliensPerRow() {
		return aliensPerRow;
	}
	
	public Boolean getEndGame() {
		return endGame;
	}
}
