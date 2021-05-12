package View;

import Controller.Main;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DeleteProductPage {
    private GUIFacilitator facilitator;
    @FXML TextField productID;
    @FXML ComboBox<Product> products;

    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();

        ObservableList<Product> productList = FXCollections.observableArrayList();
        productList.addAll(facilitator.getProductList());
        products.setItems(productList);
    }

    public void deleteProduct(ActionEvent event) {
        int ID = -1;

        try{
            ID = Integer.parseInt(productID.getText());
        } catch (Exception e) {}
        if(ID > 0) {
            facilitator.removeProduct(ID);
            facilitator.updateProductTable();
        }
        facilitator.changeWindow(event, "/fxml/ApplicationPage.fxml");

    }

    public void cancel(ActionEvent event) {
        facilitator.changeWindow(event, "/fxml/ApplicationPage.fxml");
    }
    public void close(ActionEvent event) {
        facilitator.close(event);
    }
    public void minimize(ActionEvent event) {
        facilitator.minimize(event);
    }
}
