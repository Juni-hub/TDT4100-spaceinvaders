package Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Board {
	private int score = 0;
	private int highScore = 0;
	private List<Alien> AlienGroup= new ArrayList<Alien>();
	Timer timer = new Timer();
	
	public void start() {
		TimerTask myTask = new TimerTask() {
			public void run() {
				List<Alien> AlienRow= new ArrayList<Alien>();
				for (int i = 0; i<10;i++) {
					Alien
					add()
				}
			}
		};
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
	
	
}
