package spaceInvaders;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import spaceInvaders.AppFX;

public class MainController {
	
    @FXML
    private Button playButton;

    @FXML
    private Button controlsButton;
    
    @FXML
    public void showControls(ActionEvent event) throws IOException { // Switch scene to ControlWindow
		Parent root = FXMLLoader.load(getClass().getResource("ControlWindow.fxml"));
		Scene scene = new Scene(root);
		Stage primaryStage = AppFX.getPrimaryStage();
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    public void showName(ActionEvent event) throws IOException { // Switch scene to ControlWindow
		Parent root = FXMLLoader.load(getClass().getResource("NameWindow.fxml"));
		Scene scene = new Scene(root);
		Stage primaryStage = AppFX.getPrimaryStage();
		primaryStage.setScene(scene);
		primaryStage.show();		
    }

}
