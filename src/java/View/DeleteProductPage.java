package View;

import Controller.Main;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DeleteProductPage {
    private GUIFacilitator facilitator;
    @FXML ComboBox<Product> products;
    @FXML Label lblError;

    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();

        ObservableList<Product> productList = FXCollections.observableArrayList();
        productList.addAll(facilitator.getProductList());
        products.setItems(productList);
    }

    public void deleteProduct(ActionEvent event) {
        if(products.getValue() != null) {
            int ID = products.getValue().getProductID();
            facilitator.removeProduct(ID);
            facilitator.updateProductTable();
            facilitator.updateProductTable();
            facilitator.closeSecondStage(event);
        } else {
            lblError.setText(Language.getDelProdErr());
        }
    }

    public void cancel(ActionEvent event) {
        facilitator.closeSecondStage(event);
    }
}
