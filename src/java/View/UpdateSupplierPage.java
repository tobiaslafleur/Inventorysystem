package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UpdateSupplierPage {
    private GUIFacilitator facilitator;
    @FXML private TextField name;
    @FXML private TextField phone;
    @FXML private TextField address;
    @FXML private TextField email;
    @FXML private TextField id;

    @FXML public void initialize() {
        this.facilitator = Main.getInstance().getFacilitator();
    }

    public void updateSupplier(ActionEvent e) {
        String name = null;
        String phone = null;
        String address = null;
        String email = null;
        int id = 0;


        if(!this.name.getText().equals("")) {
            name = this.name.getText();
        }
        if(!this.phone.getText().equals("")) {
            phone = this.phone.getText();
        }
        if(!this.address.getText().equals("")) {
            address = this.address.getText();
        }
        if(!this.email.getText().equals("")) {
            email = this.email.getText();
        }
        if(!this.id.getText().equals("")) {
            id = Integer.parseInt(this.id.getText());
        }

        facilitator.updateSupplier(name, phone, address, email, id);
        facilitator.changeWindow(e, "/fxml/applicationPage.fxml");
        facilitator.updateProductTable();
    }
}
