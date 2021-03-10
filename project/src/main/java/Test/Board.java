package Test;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class Board {
	int score = 0;
	int highScore = 0;
	private int gameWidth = 400;
	private int gameHeight = 600;
	
	public void createGame(Group gameDisplay) {

        //creates background
        Rectangle background = new Rectangle(0, 0, gameWidth, gameHeight);
        background.getStyleClass().add("background");
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
