package View;

import Controller.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AddShelfPage {
    private GUIFacilitator facilitator;
    @FXML private ListView<String> shelfList;
    @FXML private TextField shelfName;
    @FXML private Label errorLabel;

    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();
        initShelfList();

        shelfName.setOnKeyTyped(e -> {
            if(!shelfName.getText().equals("") || shelfName.getText() != null) {
                errorLabel.setText("");
            }
        });
    }

    public void initShelfList() {
        ObservableList<String> shelves = FXCollections.observableArrayList();
        shelves.addAll(facilitator.getShelfList());
        shelfList.setItems(shelves);
    }

    public void addShelf() {
        if(!shelfName.getText().equals("") && shelfName.getText() != null) {
            facilitator.addShelf(shelfName.getText());
            initShelfList();
        } else{
            errorLabel.setText("Shelf must have a name");
        }
    }

    public void cancel(ActionEvent event) {
        facilitator.closeSecondStage(event);
    }
}
