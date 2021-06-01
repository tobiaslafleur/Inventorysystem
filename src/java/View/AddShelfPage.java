package View;

import Controller.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddShelfPage {
    private GUIFacilitator facilitator;
    @FXML private ListView<String> shelfList;
    @FXML private TextField shelfName;
    @FXML private Label errorLabel;
    @FXML private Button add;
    @FXML private Label title;
    @FXML private Label storedShelves;
    @FXML private Hyperlink close;

    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();
        initShelfList();

        title.setText(Language.getAddShelfTitle());
        shelfName.setPromptText(Language.getAddShelfName());
        add.setText(Language.getAddShelfBtn());
        storedShelves.setText(Language.getStoredShelvesLabel());
        close.setText(Language.getAddShelfClose());

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

    public void close(ActionEvent event) {
        facilitator.closeSecondStage(event);
    }
}
