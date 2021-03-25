package spaceInvaders;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

public class GameController{
		
	private int boardWidth = 600;
	private int boardHeight = 400;

	@FXML
    private Pane pane;
	
	@FXML
	private Rectangle rectangle;
	private Player player = new Player("Ola");
	
	
	@FXML
	public void startGame() {
		System.out.println("start");
		Board board = new Board(player);
		board.startGame();
		
		
		/*TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(3));
		transition.setToX(50);
		transition.setToY(-400);
		transition.setCycleCount(Animation.INDEFINITE);
		transition.setAutoReverse(true);
		transition.setNode(rectangle);
		transition.play(); 
		*/
	}
	
	
   @FXML
    public void KeyPressed(KeyEvent event) {
    	if (event.getCode() == KeyCode.LEFT) {
    		System.out.println("left");
    		player.moveLeft();
    		moveRectangle();
  
    	} else if (event.getCode() == KeyCode.RIGHT) {
    		System.out.println("right");
    		player.moveRight();
    		moveRectangle();
    	
    	} else if (event.getCode() == KeyCode.SPACE) {
    		System.out.println("space");
    		player.shoot();
    		moveRectangle();
		}
	}
    
    @FXML
    public void moveRectangle(){
    	TranslateTransition transition = new TranslateTransition();
    	transition.setDuration(Duration.seconds(1));
    	transition.setToX(player.getPosx());
    	transition.setNode(rectangle);
    	transition.play(); 
    }

}
