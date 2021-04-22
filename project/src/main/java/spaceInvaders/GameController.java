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
		
<<<<<<< HEAD
	private int boardWidth = 600;
	private int boardHeight = 400;
	private int playerWidth = 50;
	private double alienAnimDuration = 0.1;
	private int direction = 0;
	private int targetFPS = 30;
	private int cycleDuration = 1000 / targetFPS;
	private int speed = 3;
	private int shotSpeed = 3;
	int frameCounter = 0;
	private int secondsPerAlienRow = 5;
	private double newPosX;

=======
>>>>>>> refs/remotes/origin/Filbehandling
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
	private List<Circle> shotCircles = new ArrayList<Circle>();
	
	@FXML
	public void startGame() {
		System.out.println("start");
		System.out.println(cycleDuration);
		board.startGame();
		pane.requestFocus();
		
<<<<<<< HEAD
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(cycleDuration), event -> {
			frameCounter += 1;
			newPosX = player.getPosx() + speed * direction;
			if(newPosX < -(boardWidth - playerWidth) / 2) {
				newPosX = -(boardWidth - playerWidth) / 2;
=======
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
>>>>>>> refs/remotes/origin/Filbehandling
			}
			else if(newPosX > (boardWidth - playerWidth) / 2) {
				newPosX = (boardWidth - playerWidth) / 2;
			}
			
			player.setPosx(newPosX);
			moveRectangle();
			updatePosOfShots();
			moveShots();
			if (frameCounter % (targetFPS * secondsPerAlienRow) == 0) {
				if(!board.getEndGame() == true) {
					moveAlienRow(); 
				} else {
					Platform.exit();
				}
			}
			
			List<Alien> aliens = board.getAlienGroup();
			List<Shot> shots = board.getShotGroup();
			List<Alien> aliensToRemove = new ArrayList<Alien>();
			List<Shot> shotsToRemove = new ArrayList<Shot>();
			
			for(int i=0; i < shots.size(); i++) {
				Shot shot = shots.get(i);
				for(int j=0; j < aliens.size(); j++) {
					Alien alien = aliens.get(j);
					if(shot.hitsAlien(alien.getPosx(), alien.getPosy(), alien.getRadius())) {
						aliensToRemove.add(alien);
						shotsToRemove.add(shot);
						pane.getChildren().remove(alien.getC());
						pane.getChildren().remove(shot.getC());
					}
				}
			}
			
			for(Alien alien : aliensToRemove) {
				try {
					aliens.remove(alien);
				} catch (Exception e){
					System.out.println("Already removed");
				}
			}
			
			for(Shot shot : shotsToRemove) {
				try {
					shots.remove(shot);
				} catch (Exception e){
					System.out.println("Already removed");
				}
			}
			
			board.setAlienGroup(aliens);
			board.setShotGroup(shots);
		}));
		
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	// Hvorfor funker ikke denne? Jeg vil at den skal se når piltastene blir sluppet opp :(
	// DEN FUNKER NÅ WOHOOO
	
	@FXML
	public void KeyReleased(KeyEvent event) {
		if(event.getCode() == KeyCode.LEFT) {
			if(getDirection() == -1) {
				setDirection(0);
			}
		} else if(event.getCode() == KeyCode.RIGHT) {
			if(getDirection() == 1) {
				setDirection(0);
			}
		}
	}
	
   @FXML
    public void KeyPressed(KeyEvent event) {
    	if (event.getCode() == KeyCode.LEFT) {
    		setDirection(-1);
    		// player.moveLeft();
    		moveRectangle();
  
    	} else if (event.getCode() == KeyCode.RIGHT) {
    		setDirection(1);
    		// player.moveRight();
    		moveRectangle();
    	
    	} else if (event.getCode() == KeyCode.SPACE) {
    		shoot();
		}
	}
    
    @FXML
    public void moveRectangle(){
    	TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.millis(cycleDuration));
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
    			Circle c = board.getAlienGroup().get(i).getC();
    			TranslateTransition transition = new TranslateTransition();
    			transition.setDuration(Duration.seconds(alienAnimDuration));
    			transition.setToY(board.getAlienGroup().get(i).getPosy());
    			transition.setNode(c);
    			transition.play(); 
    		}
    	}
    	board.drawAlienRow();
    	for (int i = 0; i<board.getAliensPerRow();i++) {
    		Alien alien = board.getAlienGroup().get(board.getAlienGroup().size()-i-1);
    		Circle c = alien.getC();
        	c.setRadius(alien.getRadius());
        	c.setFill(alien.getAlienColor());
        	c.setCenterX(alien.getPosx());
        	c.setCenterY(alien.getPosy());
        	pane.getChildren().add(c);
    	}
    }
    
    @FXML
    public void shoot() {
    	Circle c = new Circle();
    	Shot shot = new Shot(player.getPosx(), c);
    	board.getShotGroup().add(shot);
    	c.setCenterX(shot.getPosx());
    	c.setCenterY(shot.getPosy());
    	c.setRadius(shot.getShotRadius());
    	c.setFill(shot.getShotColor());
    	pane.getChildren().add(c);
    }
    
    public void updatePosOfShots() {
    	for(int i=0; i < board.getShotGroup().size(); i++) {
	    	Shot shot = board.getShotGroup().get(i);
			shot.setPosy(shot.getPosy() - shotSpeed);
	    }
    }
    
    
    public void moveShots() {
    	for(int i=0; i < board.getShotGroup().size(); i++) {
    		Shot shot = board.getShotGroup().get(i);
    		Circle c = shot.getC();
    		TranslateTransition transition = new TranslateTransition();
			transition.setDuration(Duration.millis(cycleDuration));
			transition.setToY(shot.getPosy());
			transition.setNode(c);
			transition.play(); 
    	}
    }

}
