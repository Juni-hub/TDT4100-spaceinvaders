package Test;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameController {
		
	private int boardWidth = 600;
	private int boardHeight = 400;

	@FXML
    private Pane pane;
	
	@FXML
	public void start(Stage stage) {
		Rectangle r = new Rectangle(0,0,boardWidth, boardHeight);
		r.setFill(Color.BLACK);
		pane.getChildren().add(r);
	}
	
		
		
	// private Player player = new Player(name);
/*
    @FXML
    public void KeyPressed(KeyEvent event) {
    	if (event.getCode() == KeyCode.LEFT) {
    		player.moveLeft();
  
    	} else if (event.getCode() == KeyCode.RIGHT) {
    		player.moveRight();
    	} else if (event.getCode() == KeyCode.SPACE) {
    		player.shoot();
		}
	}
*/
}
