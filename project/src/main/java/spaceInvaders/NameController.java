package spaceInvaders;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class NameController {
	
	@FXML
    private TextField textField;
	
	@FXML
	private AnchorPane anchorPane;
	
	@FXML
    void goToMain(ActionEvent e) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
		Scene scene = new Scene(root);
		Stage primaryStage = AppFX.getPrimaryStage();
		primaryStage.setScene(scene);
		primaryStage.show();	
    }

    @FXML
    private void storeName(KeyEvent ke) throws IOException {
    	if(ke.getCode() == KeyCode.ENTER) {
    		if(!textField.getText().isEmpty()) {
    			System.out.println(textField.getText());
    			Saver saver = new Saver("/src/main/java/spaceInvaders/gameScore");
    			if (saver.writeNameToFile(textField.getText()) == false) {
    				TextArea text = new TextArea("Ugyldig navn");
    				text.setPrefHeight(50);
    				text.setPrefWidth(200);
    				text.setLayoutY(220);
    				text.setLayoutX(200);
    				anchorPane.getChildren().add(text);
    			} else {
    				showGame();
    			}
    		}
    	}
    }
    
    @FXML
    public void showGame() throws IOException{ // Switch scene to ControlWindow
    	Parent root = FXMLLoader.load(getClass().getResource("GameWindow.fxml"));
		Scene scene = new Scene(root);
		Stage primaryStage = AppFX.getPrimaryStage();
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
    }

}
