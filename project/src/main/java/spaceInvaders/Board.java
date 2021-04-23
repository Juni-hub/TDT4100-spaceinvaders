package spaceInvaders;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Circle;

public class Board {
	
	private int aliensPerRow = 20;
	private int score;
	private int boardWidth = 600;
	private int boardHeight = 400;
	private double alienRadius = boardWidth / (2*aliensPerRow);
	private Player player;
	private List<Alien> alienGroup = new ArrayList<Alien>();
	private List<Shot> shotGroup = new ArrayList<Shot>();
	private Boolean endGame;
	
	public Board() {
		this.endGame = false;
	}
	
	public List<Shot> getShotGroup() {
		return shotGroup;
	}
	
	public void setAlienGroup(List<Alien> alienGroup) {
		this.alienGroup = alienGroup;
	}
	
	public void setShotGroup(List<Shot> shotGroup) {
		this.shotGroup = shotGroup;
	}
	
	public void drawAlienRow() {
		this.score += 10;
		for(int i = 0; i<aliensPerRow;i++) {
			Circle c = new Circle();
			Alien alien = new Alien(i*(2*alienRadius)+alienRadius,alienRadius, alienRadius, c, this);
			alienGroup.add(alien);
		}
	}
	
	public void pushAliensDown() {
		if(this.alienGroup.size() != 0) {
			for (Alien alien : alienGroup) {
				if (alien.getPosy() == alienRadius) {
					alien.setPosy(alien.getPosy()+alien.getRadius());
				} else {
					alien.setPosy(alien.getPosy()+(2 * alien.getRadius()));
				}
				if (alien.getPosy() == 300 && alien.getAlive() == true) {
					gameOver();
					break;
				}
			}
		}
	}
	
	
	public void gameOver() {
		System.out.println("GAME OVER!");
		//slette gameOver fil? eller vil vi ha historien
		//skriv highScore til fil
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
	
	public int getScore() {
		return score;
	}
	
	public void removeShot(Shot shot) {
		if (shotGroup.contains(shot)) {
			shotGroup.remove(shot);
		}
	}
	
	public int getBoardWidth() {
		return boardWidth;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setScore(int moreScore) {
		this.score += moreScore;
	}
}
