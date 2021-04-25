package spaceInvaders;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;


public class Board {
	
	private int aliensPerRow = 10;
	private int score;
	private int boardWidth = 600;
	private int boardHeight = 400;
	private double alienRadius = boardWidth / (4*aliensPerRow);
	private Player player;
	private List<Alien> alienGroup = new ArrayList<Alien>();
	private List<Shot> shotGroup = new ArrayList<Shot>();
	private Boolean endGame;
	private int alienMoveCounter = 0;
	int frameCounter = 0;
	private List<Object> objectsToBeRemoved = new ArrayList<Object>();
	private Saver saver;
	private String highScoreString;
	
	public void startGame() {
		saver = new Saver();
		String name = saver.readFromFile().get(saver.readFromFile().size()-1);
		player = new Player(name, this);
		this.endGame = false;
	}
	
	public void gameLoop() {
		objectsToBeRemoved.clear();
		player.move();
		moveShots();
	}
	
	public void alienGameLoop() {
		pushAliens();
		if(alienMoveCounter % 2 == 1) {
			drawAlienRow();
		}
	}
		
	public void moveShots() {
		for(int i = 0; i < shotGroup.size(); i++) {
			Shot shot = shotGroup.get(i);
			shot.moveShot();
			Alien hitAlien = shot.hitsAlien();
			if (hitAlien != null) {
				//objectsToBeRemoved.add(hitAlien.getC());
				objectsToBeRemoved.add(shot.getC());
				alienGroup.remove(hitAlien);
			} 
		}
	}
		
	
	public int getAlienMoveCounter() {
		return alienMoveCounter;
	}
	
	public void increaseAlienMoveCounter() {
		this.alienMoveCounter += 1;
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
			int isRight = 0;
			if(alienMoveCounter % 4 == 3) {
				// System.out.println("ISRIGHT");
				isRight = 1;
			}
			Circle c = new Circle();
			Alien alien = new Alien(2*i*(2*alienRadius)+alienRadius+isRight*(2*alienRadius),alienRadius, alienRadius, c, this);
			//alien.getC().setRadius(alien.getRadius());
            //alien.getC().setFill(alien.getAlienColor());
            //alien.getC().setCenterX(alien.getPosx());
            //alien.getC().setCenterY(alien.getPosy());
            alienGroup.add(alien);
		}
	}
	
	public String getHighScoreString() {
		return highScoreString;
	}
	
	public void setHighScoreString(String highScoreString) {
		this.highScoreString = highScoreString;
	}
	
	public void pushAliens() {
		if(this.alienGroup.size() != 0 && getAlienMoveCounter() % 4 == 1) {
			for (Alien alien : alienGroup) {
				alien.setPosx(alien.getPosx()+(2*alien.getRadius()));
			}
		} else if (this.alienGroup.size() != 0 && getAlienMoveCounter() % 4 == 3) {
			for (Alien alien : alienGroup) {
				alien.setPosx(alien.getPosx()-(2*alien.getRadius()));
			}
		} else if (this.alienGroup.size() != 0 && getAlienMoveCounter() % 2 == 0) {
				for (Alien alien : alienGroup) {
					if (alien.getPosy() == alienRadius) {
						alien.setPosy(alien.getPosy()+2*alien.getRadius());
					} else {
						alien.setPosy(alien.getPosy()+(2 * alien.getRadius()));
					}
					if (alien.getPosy() >= 300) {
						gameOver();
						break;
					}
				}
			}
		increaseAlienMoveCounter();
	}
	
	
	public void gameOver() {
		System.out.println("GAME OVER!");
		//slette gameOver fil? eller vil vi ha historien
		//skriv highScore til fil
		saver.writeToFile(";" + this.score + "\n");
		setHighScoreString(saver.getHighScore());
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
	
	public int getBoardHeight() {
		return boardHeight;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public List<Object> getObjectsToBeRemoved() {
		return objectsToBeRemoved;
	}

	
	public Arc drawArc(double centerX, double centerY, double radius, double length) {
		Arc arc = new Arc();
		arc.setFill(Color.RED);
		arc.setCenterX(centerX);
		arc.setCenterY(centerY);
		arc.setRadiusX(radius);
		arc.setRadiusY(radius);
		arc.setStartAngle(90);
		arc.setLength(length);
		arc.setType(ArcType.ROUND);
		return arc;
	}
	
	public void updateArc(Arc arc, double length) {
		arc.setLength(length);
	}
}
