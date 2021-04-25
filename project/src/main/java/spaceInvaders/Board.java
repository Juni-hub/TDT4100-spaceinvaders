package spaceInvaders;

import java.util.ArrayList;
import java.util.List;

public class Board {
	
	private Player player;
	private int score;
	private Boolean startGame;
	private Boolean endGame;
	private int aliensPerRow = 10;
	private int boardWidth = 600;
	private int boardHeight = 400;
	private List<Alien> alienGroup = new ArrayList<Alien>();
	private List<Shot> shotGroup = new ArrayList<Shot>();
	private int alienMoveCounter = 0;
	private List<Object> objectsToBeRemoved = new ArrayList<Object>();
	private Saver saver = new Saver("/src/main/java/spaceInvaders/gameScore");
	private String gameOverString;
	
	public Board() {
		this.player = new Player(saver.readFromFile().get(saver.readFromFile().size()-1).split(";")[0], this);
		this.startGame = false;
		this.endGame = false;
	}

	public void gameLoop() {
		if(score > 50000) {
			gameOver();
		}
		objectsToBeRemoved.clear();
		player.addTimeSinceLastShot();
		player.move();
		moveShots();
	}
	
	public void alienGameLoop() {
		pushAliens();
		if(alienMoveCounter % 2 == 1) {
			drawAlienRow();
			this.score += 10;
		}
	}
		
	public void moveShots() {
		for(int i = 0; i < shotGroup.size(); i++) {
			Shot shot = shotGroup.get(i);
			shot.moveShot();
			Alien hitAlien = shot.hitsAlien();
			if (hitAlien != null) {
				this.score += 10;
				objectsToBeRemoved.add(hitAlien);
				objectsToBeRemoved.add(shot);
				shotGroup.remove(shot);
				alienGroup.remove(hitAlien);
			} 
		}
	}
	
	public void drawAlienRow() {
		int alienRadius = boardWidth / (4*aliensPerRow);
		for(int i = 0; i<aliensPerRow;i++) {
			int isRight = 0;
			if(alienMoveCounter % 4 == 3) {
				isRight = 1;
			}
			Alien alien = new Alien(2*i*(2*alienRadius)+alienRadius+isRight*(2*alienRadius),alienRadius, alienRadius);
            alienGroup.add(alien);
		}
	}
	
	public void pushAliens() {
		if(this.alienGroup.size() != 0 && this.alienMoveCounter % 4 == 1) {
			for (Alien alien : alienGroup) {
				alien.setPosx(alien.getPosx()+(2*alien.getRadius()));
			}
		} else if (this.alienGroup.size() != 0 && this.alienMoveCounter % 4 == 3) {
			for (Alien alien : alienGroup) {
				alien.setPosx(alien.getPosx()-(2*alien.getRadius()));
			}
		} else if (this.alienGroup.size() != 0 && this.alienMoveCounter % 2 == 0) {
				for (Alien alien : alienGroup) {
					if (alien.getPosy() == alien.getRadius()) {
						alien.setPosy(alien.getPosy()+2*alien.getRadius());
					} else {
						alien.setPosy(alien.getPosy()+(2 * alien.getRadius()));
					}
					if (alien.getPosy() >= 300) {
						gameOver();
					}
				}
			}
		this.alienMoveCounter += 1;
	}
	
	public void removeShot(Shot shot) {
		if (shotGroup.contains(shot)) {
			shotGroup.remove(shot);
		}
	}
	
	
	public void gameOver() {
		if(!this.endGame) {
			System.out.println("GAME OVER!");
			saver.writeScoreToFile("" + this.score);
			this.gameOverString = "GAME OVER \nPlayer: " + player.getName() + "\nScore: " + this.score + "\n\n" + saver.getHighScore();
			this.endGame = true;
		}
	}
	
	public String getGameOverString() {
		return gameOverString;
	}
	
	public List<Alien> getAlienGroup() {
		return alienGroup;
	}
	
	public Boolean getEndGame() {
		return endGame;
	}
	
	public int getScore() {
		return score;
	}
	
	public List<Shot> getShotGroup() {
		return shotGroup;
	}
	
	public int getBoardWidth() {
		return boardWidth;
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
	
	public void setStartGame(Boolean startGame) {
		this.startGame = startGame;
	}
	
	public Boolean getStartGame() {
		return startGame;
	}
}
