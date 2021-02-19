package Test;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControlController {

    @FXML
    void goToMain(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
		Scene scene = new Scene(root);
		Stage primaryStage = TestFX.getPrimaryStage();
		primaryStage.setScene(scene);
		primaryStage.show();	
    }

}
