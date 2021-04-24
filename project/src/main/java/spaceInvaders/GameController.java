package spaceInvaders;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;

public class GameController{
		
	private double alienAnimDuration = 0.1;
	private int targetFPS = 30;
	private int cycleDuration = 1000 / targetFPS;
	int frameCounter = 0;
	private int secondsPerAlienRow = 2;

	@FXML
    private Pane pane;
	@FXML
	private Rectangle rectangle;
	@FXML
	private TextField score;
	@FXML
	private BorderPane borderPane;
	
	Saver saver = new Saver();
	String name = saver.readFromFile().get(saver.readFromFile().size()-1);
	private Board board = new Board();
	Player player = new Player(name, board);
	
	@FXML
	public void startGame() {
		pane.requestFocus();
		board.startGame();
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(cycleDuration), event -> {
			frameCounter += 1;
			if(!board.getEndGame() == true) {
				score.setText("Score: " + board.getScore());
				board.gameLoop();
				checkObjectsToBeRemoved();
				moveRectangle();
				if (frameCounter % (targetFPS * secondsPerAlienRow) == 0) {
					board.alienGameLoop();
					addAliens();
				}
			} else {
				TextArea text = new TextArea("GAME OVER \nPlayer: " + board.getPlayer().getName() + "\nScore: " + board.getScore());
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
    		Shot shot = board.getPlayer().shoot();
    		pane.getChildren().add(shot.getC());
		}
	}
    
    @FXML
    public void moveRectangle(){
    	rectangle.setLayoutX(player.getPosx());
    	TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.millis(cycleDuration));
		transition.setToX(board.getPlayer().getPosx());
		transition.setNode(rectangle);
		transition.play(); 
    }
    
    /*@FXML
    public void moveAlienRow() {
    	board.pushAliens();
    	for(int i = 0; i < board.getAlienGroup().size(); i++) {
        	Alien alien = board.getAlienGroup().get(i);
        	TranslateTransition transition = new TranslateTransition();
        	transition.setDuration(Duration.seconds(alienAnimDuration));
        	transition.setToY(alien.getPosy());
        	transition.setNode(alien.getC());
        	transition.play(); 
        }
    	board.drawAlienRow();
    	for (int i = 0; i<board.getAliensPerRow();i++) {
        	Alien alien = board.getAlienGroup().get(board.getAlienGroup().size()-i-1);
            alien.getC().setRadius(alien.getRadius());
            alien.getC().setFill(alien.getAlienColor());
            alien.getC().setCenterX(alien.getPosx());
            alien.getC().setCenterY(alien.getPosy());
            pane.getChildren().add(alien.getC());
        	}
    	}*/
    
   /* @FXML
   public void shoot() {
    	Circle c = new Circle();
    	Shot shot = new Shot(player.getPosx(), c,board);
    	board.getShotGroup().add(shot);
    	shot.getC().setCenterX(shot.getPosx());
    	shot.getC().setCenterY(shot.getPosy());
    	shot.getC().setRadius(shot.getShotRadius());
    	shot.getC().setFill(shot.getShotColor());
    	pane.getChildren().add(shot.getC());
    }*/
    
    public void checkObjectsToBeRemoved() {
    	for(int i = 0; i < board.getObjectsToBeRemoved().size(); i++) {
    			Object o = board.getObjectsToBeRemoved().get(i);
    			pane.getChildren().remove(o);
    			// System.out.println("REMOVED");
    		}
    	}
    
    public void addAliens() {
    	for (int i = 0; i < board.getAlienGroup().size();i++) {
    		Alien alien = board.getAlienGroup().get(i);
    		if(!pane.getChildren().contains(alien.getC())) {
    			pane.getChildren().add(alien.getC());
    		}
    		
    	}
    }
}

