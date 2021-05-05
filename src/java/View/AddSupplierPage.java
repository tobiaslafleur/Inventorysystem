package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;

public class AddSupplierPage {
    private static AddSupplierPage instance;
    private GUIFacilitator facilitator;

    @FXML private TextField name = new TextField();
    @FXML private TextField phone = new TextField();
    @FXML private TextField address = new TextField();
    @FXML private TextField email = new TextField();

    @FXML public void initialize() {
        instance = this;
        facilitator = Main.getInstance().getFacilitator();
        setInstance();
    }

    private void setInstance() {
        facilitator.setAddSupplierInstance(instance);
    }

    public void addSupplier(ActionEvent event){
        String supName = name.getText();
        String supPhone = phone.getText();
        String supAddress = address.getText();
        String supEmail = email.getText();

        if(facilitator.addSupplier(supName, supPhone, supAddress, supEmail)) {
            facilitator.changeWindow(event, "/fxml/applicationPage.fxml");
        } else {
            //TODO: Label saying: "Failed to add supplier."
            //temp:
            JOptionPane.showMessageDialog(null, "Failed to add supplier");
        }
    }
    public void cancelSupplier(ActionEvent supplierCancelled) {
        facilitator.changeWindow(supplierCancelled, "/fxml/applicationPage.fxml");
    }
}
