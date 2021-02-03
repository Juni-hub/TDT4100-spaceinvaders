package Test;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class gameController {
	
    @FXML
    private Button playButton;

    @FXML
    private Button controlsButton;
    
    @FXML
    void showControls(MouseEvent event) {
    	System.out.println("Controls");
    }

    @FXML
    void startGame(MouseEvent event) {
    	System.out.println("Play");
    }

}
