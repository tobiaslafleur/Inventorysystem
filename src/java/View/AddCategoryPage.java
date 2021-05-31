package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class AddCategoryPage {

    private GUIFacilitator facilitator;
    @FXML private TextField categoryName;
    @FXML private Button addCategoryBtn;
    @FXML private Hyperlink cancel;
    @FXML private Label createCategory;

    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();

        addCategoryBtn.setText(Language.getAddCatButton());
        cancel.setText(Language.getAddCatCancel());
        createCategory.setText(Language.getAddCatCreateCategory());

        categoryName.setOnKeyTyped(actionEvent -> {
            if(!categoryName.getText().isEmpty()) {
                categoryName.setStyle("-fx-border-color: #1F701D");
                lblError.setText("");
            } else {
                categoryName.setStyle("-fx-border-color: #974F4F;");
                lblError.setText(Language.getAddCatError());
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
            lblError.setText(Language.getAddCatError());
        }
    }

    public void cancel(ActionEvent event) {
        facilitator.closeSecondStage(event);
    }
}
