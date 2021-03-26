package spaceInvaders;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;


public class GameController{
		
	private int boardWidth = 600;
	private int boardHeight = 450;

	@FXML
    private Pane pane;
	
	@FXML
	private Rectangle rectangle;
	private Player player = new Player("Ola");
	private Board board = new Board(player);
	
	
	@FXML
	public void startGame() {
		System.out.println("start");
		board.startGame();
		pane.requestFocus();
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> {
			if(!board.getEndGame() == true) {
				moveAlienRow(); 
			} else {
				Platform.exit();
			}
		}));
		
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
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
		transition.setDuration(Duration.seconds(0.5));
		transition.setToX(player.getPosx());
		transition.setNode(rectangle);
		transition.play(); 
		
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
    public void moveAlienRow() {
    	if(board.getAlienGroup().size() != 0 ) {
    		board.pushAliensDown();
    		for (int i = 0; i<board.getAlienGroup().size();i++) {
    			ObservableList<Node> children = pane.getChildren();
    			Node c = children.get(i);
    			TranslateTransition transition = new TranslateTransition();
    			transition.setDuration(Duration.seconds(0.5));
    			transition.setToY(board.getAlienGroup().get(i).getPosy());
    			transition.setNode(c);
    			transition.play(); 
    		}
    	}
    	board.drawAlienRow();
    	for (int i = 0; i<board.getAliensPerRow();i++) {
    		Alien alien = board.getAlienGroup().get(board.getAlienGroup().size()-i-1);
    		Circle c = new Circle();
        	c.setRadius(alien.getRadius());
        	c.setFill(alien.getAlienColor());
        	c.setCenterX(alien.getPosx());
        	c.setCenterY(alien.getPosy());
        	pane.getChildren().add(c);
    	}
    }

}
