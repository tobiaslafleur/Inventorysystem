package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

public class UpdateProductPage {
    private GUIFacilitator facilitator;
    @FXML TextField id;
    @FXML TextField name;
    @FXML TextField quantity;
    @FXML TextField price;
    @FXML TextField shelfPosition;
    @FXML TextField cost;

    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();
    }

    public void updateProduct(ActionEvent e) {
        int id = 0;
        String name = null;
        int quantity = 0;
        BigDecimal price = new BigDecimal("0.0");
        String shelf = null;
        BigDecimal cost = new BigDecimal("0.0");

        if(!this.id.getText().equals("")) {
            id = Integer.parseInt(this.id.getText());
        }
        if(!this.name.getText().equals("")) {
            name = this.name.getText();
        }
        if(!this.quantity.getText().equals("")) {
            quantity = Integer.parseInt(this.quantity.getText());
        }
        if(!this.price.getText().equals("")) {
            price = new BigDecimal(this.price.getText());
        }
        if(!shelfPosition.getText().equals("")) {
            shelf = this.shelfPosition.getText();
        }
        if(!this.cost.getText().equals("")) {
            cost = new BigDecimal(this.price.getText());
        }


        facilitator.updateProduct(id, name, quantity, price, shelf,  cost);
        facilitator.changeWindow(e, "/fxml/applicationPage.fxml");
        facilitator.updateProductTable();
    }

    public void cancelUpdateProduct (ActionEvent cancelUpdateProduct) {
        facilitator.changeWindow(cancelUpdateProduct, "/fxml/applicationPage.fxml");
    }
}
