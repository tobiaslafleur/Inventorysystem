package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddProductPage {
    private GUIFacilitator facilitator;
    @FXML private TextField name;
    @FXML private TextField stock;
    @FXML private TextField price;
    @FXML private TextField categoryID;
    @FXML private TextField shelfPosition;
    @FXML private TextField supplierID;
    @FXML private TextField cost;
    @FXML private TextField userID;

    @FXML public void initialize(){
        facilitator = Main.getInstance().getFacilitator();
    }

    public void addProduct(ActionEvent e) {
        facilitator.addProduct(name.getText(), stock.getText(), price.getText(), categoryID.getText(), shelfPosition.getText(), supplierID.getText(), cost.getText());
        facilitator.changeWindow(e, "/fxml/applicationPage.fxml");
        facilitator.updateProductTable();
    }
    public void cancelAddProduct (ActionEvent cancelAddProduct) {
        facilitator.changeWindow(cancelAddProduct, "/fxml/applicationPage.fxml");
    }
}
