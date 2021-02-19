package Test;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class GameController {

		@FXML
	    private Rectangle player;

	    @FXML
	    public void KeyPressed(KeyEvent event) {
	    	if (event.getCode() == KeyCode.LEFT) {
	    		player.setPos(player.getPos() - 1);
	  
	    	} else if (event.getCode() == KeyCode.RIGHT) {
	    		player.setPos(player.getPos() + 1);
	    	} else if (event.getCode() == KeyCode.SPACE) {
	    		player.shoot();

	    }

	}

}
