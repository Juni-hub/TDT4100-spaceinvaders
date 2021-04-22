package spaceInvaders;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;


public class GameController{
		
	@FXML
    private Pane pane;
	@FXML
	private Rectangle rectangle;
	@FXML
	private TextField score;
	@FXML
	private BorderPane borderPane;
	
	private Saver saver = new Saver();
	private String name = saver.readFromFile().get(saver.readFromFile().size()-1);
	private Player player = new Player(name);
	private Board board = new Board(player);
	private List<Circle> alienCircles = new ArrayList<Circle>();
	
	@FXML
	public void startGame() {
		System.out.println("start");
		board.startGame();
		pane.requestFocus();
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(6), event -> {
			if(!board.getEndGame() == true) {
				moveAlienRow();
				score.setText("Score: " + board.getScore());
			} else {
				TextArea text = new TextArea("GAME OVER \n Score: " + board.getScore());
				Font font = new Font("Segoe Script",20);
				text.setFont(font);
				text.setPrefHeight(200);
				text.setPrefWidth(200);
				text.setLayoutY(50);
				text.setLayoutX(200);
				text.setStyle("-fx-font-alignment: center");
				pane.getChildren().add(text);
				//Platform.exit();
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
    		shoot();
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
    			Circle c = alienCircles.get(i);
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
        	alienCircles.add(c);
        	pane.getChildren().add(c);
    	}
    }
    
    @FXML
    public void shoot() {
    	Shot shot = new Shot(player.getPosx());
    	Circle c = new Circle();
    	c.setRadius(shot.getShotRadius());
    	c.setFill(shot.getShotColor());
    	c.setCenterX(shot.getPosx());
    	c.setCenterY(shot.getPosy());
    	pane.getChildren().add(c);
    	while (shot.getHit() == false && shot.getPosy() != 0) {
    		shot.moveShot(board);
    		
    		TranslateTransition transition = new TranslateTransition();
			transition.setDuration(Duration.seconds(0.5));
			transition.setToY(shot.getPosy());
			transition.setNode(c);
			transition.play(); 
    	}
		
    	
    }

}
