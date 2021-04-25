package spaceInvaders;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;

public class GameController{
		
	private int targetFPS = 30;
	private int cycleDuration = 1000 / targetFPS;
	int frameCounter = 0;
	private int secondsPerAlienRow = 2;
	private int framesBetweenShots;
	private int framesSinceLastShot;
	private double framePercent;

	@FXML
    private Pane pane;
	@FXML
	private Rectangle rectangle;
	@FXML
	private TextField score;
	@FXML
	private BorderPane borderPane;
	
	// Saver saver = new Saver();
	// String name = saver.readFromFile().get(saver.readFromFile().size()-1);
	private Board board = new Board();
	// Player player = new Player(name, board);
	
	@FXML
	public void startGame() {
		pane.requestFocus();
		board.startGame();
		
		Arc shotTime = board.drawArc(25, 375, 20, 0);
		
		pane.getChildren().add(shotTime);
		
		framesBetweenShots = (int) Math.ceil(board.getPlayer().getShotDelaySeconds() * targetFPS);
		framesSinceLastShot = framesBetweenShots;
				
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(cycleDuration), event -> {
			frameCounter += 1;
			framesSinceLastShot += 1;
			if(!board.getEndGame() == true) {
				score.setText("Score: " + board.getScore());
				board.gameLoop();
				checkObjectsToBeRemoved();
				moveRectangle();
				framePercent = ((double)framesSinceLastShot/framesBetweenShots);
				board.updateArc(shotTime, Math.min(360.0, 360.0*framePercent));
				if (frameCounter % (targetFPS * secondsPerAlienRow) == 0) {
					board.alienGameLoop();
					//addAliens();
				}
				for(Alien alien : board.getAlienGroup()) {
					Circle c = new Circle();
					c.setRadius(alien.getRadius());
		            c.setFill(Color.GREEN);
		            c.setCenterX(alien.getPosx());
		            c.setCenterY(alien.getPosy());
				}
			} else {
				TextArea text = new TextArea("GAME OVER \nPlayer: " + board.getPlayer().getName() + "\nScore: " + board.getScore() + "\nHIGH SCORE\n" + board.getHighScoreString());
				Font font = new Font("Segoe Script",20);
				text.setFont(font);
				text.setPrefHeight(200);
				text.setPrefWidth(200);
				text.setLayoutY(50);
				text.setLayoutX(200);
				pane.getChildren().add(text);
			}
		}
		));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	@FXML
	public void KeyReleased(KeyEvent event) {
		if(event.getCode() == KeyCode.LEFT && board.getPlayer().getDirection() == -1) {
			board.getPlayer().setDirection(0);
		}
		else if(event.getCode() == KeyCode.RIGHT && board.getPlayer().getDirection() == 1){
			board.getPlayer().setDirection(0);
		}
	}
	
   @FXML
    public void KeyPressed(KeyEvent event) {
	   if (event.getCode() == KeyCode.LEFT) {
		   board.getPlayer().setDirection(-1);
    		
  
    	} else if (event.getCode() == KeyCode.RIGHT) {
    		board.getPlayer().setDirection(1);
    	
    	} else if (event.getCode() == KeyCode.SPACE) {
    		if(framesSinceLastShot >= framesBetweenShots) {
    			Shot shot = board.getPlayer().shoot();
    			pane.getChildren().add(shot.getC());
    			framesSinceLastShot = 0;
    		}
		}
	}
    
    @FXML
    public void moveRectangle(){
    	rectangle.setLayoutX(board.getPlayer().getPosx());
    	TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.millis(cycleDuration));
		transition.setToX(board.getPlayer().getPosx());
		transition.setNode(rectangle);
		transition.play(); 
    }
    
    
    public void checkObjectsToBeRemoved() {
    	for(int i = 0; i < board.getObjectsToBeRemoved().size(); i++) {
    			Object o = board.getObjectsToBeRemoved().get(i);
    			pane.getChildren().remove(o);
    			// System.out.println("REMOVED");
    		}
    	}
    
    /*public void addAliens() {
    	for (int i = 0; i < board.getAlienGroup().size();i++) {
    		Alien alien = board.getAlienGroup().get(i);
    		if(!pane.getChildren().contains(alien.getC())) {
    			pane.getChildren().add(alien.getC());
    		}
    	}
    }*/
}

