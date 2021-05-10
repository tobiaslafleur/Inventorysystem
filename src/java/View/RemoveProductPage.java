package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RemoveProductPage {
    private GUIFacilitator facilitator;
    @FXML TextField productID;

    @FXML public void initialize() {
        facilitator = Main.getInstance().getFacilitator();
    }

    public void deleteProduct(ActionEvent event) {
        int ID = -1;

        try{
            ID = Integer.parseInt(productID.getText());
        } catch (Exception e) {}
        if(ID > 0) {
            facilitator.removeProduct(ID);
            facilitator.changeWindow(event, "/fxml/MainAppPage.fxml");
            facilitator.updateProductTable();
        }
    }

    public void cancel(ActionEvent event) {
        facilitator.changeWindow(event, "/fxml/MainAppPage.fxml");
    }
    public void close(ActionEvent event) {
        facilitator.close(event);
    }
    public void minimize(ActionEvent event) {
        facilitator.minimize(event);
    }
}
