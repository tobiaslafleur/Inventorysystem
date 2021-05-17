package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class AddCategoryPage {

    private GUIFacilitator facilitator;
    @FXML private TextField categoryName;
    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();
    }

    public void addCategory(ActionEvent e) {
        facilitator.createCategory(categoryName.getText());
        facilitator.closeSecondStage(e);
    }

    public void cancel(ActionEvent event) {
        facilitator.closeSecondStage(event);
    }
}
