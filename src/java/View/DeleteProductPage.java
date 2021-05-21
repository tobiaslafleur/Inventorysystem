package View;

import Controller.Main;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DeleteProductPage {
    private GUIFacilitator facilitator;
    @FXML ComboBox<Product> products;
    @FXML Label lblError;
    @FXML Button btnDel;
    @FXML Label lblTitle;
    @FXML Hyperlink hyperCancel;

    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();

        ObservableList<Product> productList = FXCollections.observableArrayList();
        productList.addAll(facilitator.getProductList());
        products.setItems(productList);

        products.setPromptText(Language.getDelProds());
        lblTitle.setText(Language.getDelTitle());
        btnDel.setText(Language.getDelTitle());
        hyperCancel.setText(Language.getProdCancel());
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
