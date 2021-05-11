package View;

import Controller.Main;
import Model.Category;
import Model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
    @FXML private ComboBox<Category> categories;
    @FXML private TextField shelfPosition;
    @FXML private ComboBox<Supplier> suppliers;
    @FXML private TextField supplierID;
    @FXML private TextField cost;

    /**
     * Initializes the connection between this class and GUIFacilitator.
     */
    @FXML public void initialize(){
        facilitator = Main.getInstance().getFacilitator();
//        fillComboBoxes();

    }

    public void fillComboBoxes() {
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
        supplierList.addAll(facilitator.getSupplierList());
        suppliers.setItems(supplierList);

        ObservableList<Category> categoryList = FXCollections.observableArrayList();
        categoryList.addAll(facilitator.getCategoryList());
        categories.setItems(categoryList);
    }

    /**
     * Adds a product to the database.
     * @param e     Event that triggers the method.
     */
    public void addProduct(ActionEvent e) {
        facilitator.addProduct(name.getText(), stock.getText(), price.getText(), categoryID.getText(), shelfPosition.getText(), supplierID.getText(), cost.getText());
        facilitator.changeWindow(e, "/fxml/ApplicationPage.fxml");
        facilitator.updateProductTable();
    }

    /**
     * Cancels the page and goes back to ApplicationPage.
     * @param cancelAddProduct  Event that triggers the method.
     */
    public void cancel(ActionEvent cancelAddProduct) {
        facilitator.changeWindow(cancelAddProduct, "/fxml/ApplicationPage.fxml");
    }
    public void close(ActionEvent event) {
        facilitator.close(event);
    }
    public void minimize(ActionEvent event) {
        facilitator.minimize(event);
    }
}
