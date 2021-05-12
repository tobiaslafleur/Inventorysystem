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
        facilitator.changeWindow(e, "/fxml/ApplicationPage.fxml");
    }

    public void cancel(ActionEvent cancelCategory) {
        facilitator.changeWindow(cancelCategory, "/fxml/ApplicationPage.fxml");
    }
    public void close(ActionEvent event) {
        facilitator.close(event);
    }
    public void minimize(ActionEvent event) {
        facilitator.minimize(event);
    }
}
