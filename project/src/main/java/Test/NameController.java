package Test;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

public class NameController {

    @FXML
    void storeName(KeyEvent event) {
    	System.out.println(event.getCode());
    }

}
