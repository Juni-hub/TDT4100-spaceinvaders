package Test;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class TestFX extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)(FXMLLoader.load(getClass().getResource("Test.fxml")));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
