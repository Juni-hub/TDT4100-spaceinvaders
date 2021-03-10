package Test;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class NameController {
	
	@FXML
    private TextField textField;
	private String name;
	
	@FXML
    void goToMain(ActionEvent e) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
		Scene scene = new Scene(root);
		Stage primaryStage = TestFX.getPrimaryStage();
		primaryStage.setScene(scene);
		primaryStage.show();	
    }

    @FXML
    private void storeName(KeyEvent ke) throws IOException {
    	if(ke.getCode() == KeyCode.ENTER) {
    		if(!textField.getText().isEmpty()) {
    			System.out.println(textField.getText());
    			// setName(textField.getText());
    			showGame();
    		}
    	}
    }
    
    public void showGame() throws IOException{ // Switch scene to ControlWindow
		Parent root = FXMLLoader.load(getClass().getResource("GameWindow.fxml"));
		Scene scene = new Scene(root);
		Stage primaryStage = TestFX.getPrimaryStage();
		primaryStage.setScene(scene);
		primaryStage.show();		
    }
    
    public String getName() {
		return name;
	}
    
    public void setName(String name) {
		this.name = name;
	}

}
