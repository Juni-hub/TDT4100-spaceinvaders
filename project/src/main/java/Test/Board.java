package Test;

<<<<<<< HEAD
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
=======
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
>>>>>>> branch 'Game' of https://gitlab.stud.idi.ntnu.no/tdt4100/v2021/student_projects/tdt4100-prosjekt-oskarjor.git

public class Board {
<<<<<<< HEAD
	int score = 0;
	int highScore = 0;
	private int gameWidth = 400;
	private int gameHeight = 600;
	
	public void createGame(Group gameDisplay) {

        //creates background
        Rectangle background = new Rectangle(0, 0, gameWidth, gameHeight);
        background.getStyleClass().add("background");
	}
=======
	private int score = 0;
	private int highScore = 0;
	private List<Alien> AlienGroup= new ArrayList<Alien>();
	Timer timer = new Timer();
>>>>>>> branch 'Game' of https://gitlab.stud.idi.ntnu.no/tdt4100/v2021/student_projects/tdt4100-prosjekt-oskarjor.git
	
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
	
	public static void main(String[] args) {
	}
	
	
}
