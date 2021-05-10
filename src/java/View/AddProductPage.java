package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Page where the user adds a product.
 */
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

    /**
     * Initializes the connection between this class and GUIFacilitator.
     */
    @FXML public void initialize(){
        facilitator = Main.getInstance().getFacilitator();
    }

    /**
     * Adds a product to the database.
     * @param e     Event that triggers the method.
     */
    public void addProduct(ActionEvent e) {
        facilitator.addProduct(name.getText(), stock.getText(), price.getText(), categoryID.getText(), shelfPosition.getText(), supplierID.getText(), cost.getText());
        facilitator.changeWindow(e, "/fxml/MainAppPage.fxml");
        facilitator.updateProductTable();
    }

    /**
     * Cancels the page and goes back to ApplicationPage.
     * @param cancelAddProduct  Event that triggers the method.
     */
    public void cancelAddProduct (ActionEvent cancelAddProduct) {
        facilitator.changeWindow(cancelAddProduct, "/fxml/MainAppPage.fxml");
    }
}
