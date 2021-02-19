package Test;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class NameController {
	
	@FXML
    void goToMain(ActionEvent e) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
		Scene scene = new Scene(root);
		Stage primaryStage = TestFX.getPrimaryStage();
		primaryStage.setScene(scene);
		primaryStage.show();	
    }

    @FXML
    private void storeName(KeyEvent ke) {
    	if(ke.getCode() == KeyCode.ENTER) {
    		System.out.println();
    	}
    }

}
