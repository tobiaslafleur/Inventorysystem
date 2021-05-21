package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class AddCategoryPage {

    private GUIFacilitator facilitator;
    @FXML private TextField categoryName;
    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();

        categoryName.setOnKeyTyped(actionEvent -> {
            if(!categoryName.getText().isEmpty()) {
                categoryName.setStyle("-fx-border-color: #1F701D");
                lblError.setText("");
            } else {
                categoryName.setStyle("-fx-border-color: #974F4F;");
                lblError.setText(Language.getCategoryError());
            }
        });
    }
    @FXML Label lblError;

    public void addCategory(ActionEvent e) {
        if(!categoryName.getText().isEmpty() || categoryName.getText() == null) {
            facilitator.createCategory(categoryName.getText());
            facilitator.updateCategoryTable();
            facilitator.closeSecondStage(e);
        } else {
            lblError.setText(Language.getCategoryError());
        }

    }

    public void cancel(ActionEvent event) {
        facilitator.closeSecondStage(event);
    }
}
