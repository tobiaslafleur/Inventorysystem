package View;

import Controller.Main;
import Model.Product;
import Model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EditSupplierPage {
    private GUIFacilitator facilitator;
    @FXML private TextField name;
    @FXML private TextField phone;
    @FXML private TextField address; //TODO remove this one when the new GUI is implemented
    @FXML private TextField street;
    @FXML private TextField city;
    @FXML private TextField country;
    @FXML private TextField email;
    @FXML private TextField id; //TODO Remove also
    @FXML private ComboBox<Supplier> suppliers;

    @FXML public void initialize() {
        this.facilitator = Main.getInstance().getFacilitator();

//        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
//        supplierList.addAll(facilitator.getSupplierList());
//        suppliers.setItems(supplierList);
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
        facilitator.changeWindow(e, "/fxml/ApplicationPage.fxml");
        facilitator.updateProductTable();
    }
    public void cancel(ActionEvent cancelUpdateSupplier) {
        facilitator.changeWindow(cancelUpdateSupplier, "/fxml/ApplicationPage.fxml");
    }
    public void close(ActionEvent event) {
        facilitator.close(event);
    }
    public void minimize(ActionEvent event) {
        facilitator.minimize(event);
    }
}
