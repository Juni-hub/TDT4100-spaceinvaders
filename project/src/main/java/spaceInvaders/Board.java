package spaceInvaders;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Board {
	
	private int aliensPerRow = 20;
	private int boardWidth = 600;
	private int boardHeight = 400;
	private double alienRadius = boardWidth / (2*aliensPerRow);
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
			Alien alien = new Alien(i*(2*alienRadius)+alienRadius,alienRadius, alienRadius);
			alienGroup.add(alien);
			
		}
	}
	
	public void pushAliensDown() {
		for (int i = 0; i <alienGroup.size();i++) {
			if (alienGroup.get(i).getPosy() == alienRadius) {
				alienGroup.get(i).setPosy(alienGroup.get(i).getPosy()+alienRadius);
			} else {
			alienGroup.get(i).setPosy(alienGroup.get(i).getPosy()+(2 * alienRadius));
			}
		}
		
		for (int i=0; i<aliensPerRow;i++) {
			if (alienGroup.get(i).getPosy() == 300) {
				gameOver();
			}
		}
	}
	
	
	public void gameOver() {
		System.out.println("GAME OVER!");
		this.endGame = true;
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
