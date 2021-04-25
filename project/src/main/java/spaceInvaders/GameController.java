package spaceInvaders;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Hashtable;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;

public class GameController{
		
	private int targetFPS = 30;
	private int cycleDuration = 1000 / targetFPS;
	private int frameCounter = 0;
	private int secondsPerAlienRow = 2;
	
	private Color alienColor = Color.GREEN;
	private Color shotColor = Color.BLACK;

	@FXML
    private Pane pane;
	@FXML
	private Rectangle rectangle;
	@FXML
	private TextArea score;
	@FXML
	private BorderPane borderPane;
	
	private Board board = new Board();
	private Hashtable<Alien, Circle> alienGroup= new Hashtable<Alien, Circle>();
	private Hashtable<Shot, Circle> shotGroup= new Hashtable<Shot, Circle>();
	
	@FXML
	public void startGame() {
		if (!board.getStartGame()) {
		board.setStartGame(true);
		pane.requestFocus();
		
		Arc shotTime = new Arc();
		shotTime.setCenterX(25);
		shotTime.setCenterY(375);
		shotTime.setRadiusX(15);
		shotTime.setRadiusY(15);
		shotTime.setStartAngle(90);
		shotTime.setLength(0);
		shotTime.setType(ArcType.ROUND);
		pane.getChildren().add(shotTime);

		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(cycleDuration), event -> {
			frameCounter += 1;
						
			if(!board.getEndGame() == true) {
				score.setText("Score: " + board.getScore());
							
				board.gameLoop();
				moveRectangle();
				moveShots();
				shotTime.setLength(Math.min(360.0, 360.0*((double)board.getPlayer().getTimeSinceLastShot()/board.getPlayer().getTimeBeetweenShots())));
				checkObjectsToBeRemoved();
				
				if (frameCounter % (targetFPS * secondsPerAlienRow) == 0) {
					board.alienGameLoop();
					moveAliens();
				}
			} else {
				TextArea text = new TextArea(board.getGameOverString());
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
    			Shot shot = board.getPlayer().shoot();
    			if (shot != null) {
    				Circle c = new Circle();
    				c.setRadius(shot.getRadius());
    				c.setFill(shotColor);
    				c.setCenterX(shot.getPosx());
    				c.setCenterY(shot.getPosy());
    				shotGroup.put(shot, c);
    				pane.getChildren().add(c);
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
    			Object removeNode;
    			Object o = board.getObjectsToBeRemoved().get(i);
    			if (alienGroup.containsKey(o)) {
    				removeNode = alienGroup.get(o);
    			} else {
    				removeNode = shotGroup.get(o);
    			}
    			pane.getChildren().remove(removeNode);
    		}
    	}
    
    public void moveAliens() {
    	for(Alien alien : board.getAlienGroup()) {
    		if (alienGroup.containsKey(alien)) {
    			alienGroup.get(alien).setCenterX(alien.getPosx());
    			alienGroup.get(alien).setCenterY(alien.getPosy());
    		} else {
    			Circle c = new Circle();
    			c.setRadius(alien.getRadius());
                c.setFill(alienColor);
                c.setCenterX(alien.getPosx());
                c.setCenterY(alien.getPosy());
                pane.getChildren().add(c);
                alienGroup.put(alien,c);
    		}
    	}
    }
    
    public void moveShots() {
    	for(Shot shot : board.getShotGroup()) {
    		shotGroup.get(shot).setCenterY(shot.getPosy());
    	}
    }
}
    

