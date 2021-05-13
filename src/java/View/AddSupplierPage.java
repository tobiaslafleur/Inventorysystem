package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.swing.*;

public class AddSupplierPage {
    private static AddSupplierPage instance;
    private GUIFacilitator facilitator;

    @FXML private TextField name;
    @FXML private TextField phone;
    @FXML private TextField street;
    @FXML private TextField city;
    @FXML private TextField country;
    @FXML private TextField email;

    /**
     * Intializes the connection between GUIFacilitator and this class.
     */
    @FXML public void initialize() {
        instance = this;
        facilitator = Main.getInstance().getFacilitator();
        setInstance();
    }

    /**
     * Intializes the connection between GUIFacilitator and this class.
     */
    private void setInstance() {
        facilitator.setAddSupplierInstance(instance);
    }

    /**
     * Adds the supplier to the database.
     * @param event     The event that triggers the method.
     */
    public void addSupplier(ActionEvent event) {
        String supName = name.getText();
        String supPhone = phone.getText();
        String supStreet = street.getText();
        String supCity = city.getText();
        String supCountry = country.getText();
        String supEmail = email.getText();

        if(facilitator.addSupplier(supName, supPhone, supStreet, supCity, supCountry, supEmail)) {
            facilitator.changeWindow(event, "/fxml/ApplicationPage.fxml");
        } else {
            //TODO: Label saying: "Failed to add supplier."
            //temp:
            JOptionPane.showMessageDialog(null, "Failed to add supplier");
        }
    }

    /**
     * Cancels the operation and returns to ApplicationPage.
     * @param supplierCancelled The event that triggers the method.
     */
    public void cancel(ActionEvent supplierCancelled) {
        facilitator.changeWindow(supplierCancelled, "/fxml/ApplicationPage.fxml");
    }
    public void close(ActionEvent event) {
        facilitator.close(event);
    }
    public void minimize(ActionEvent event) {
        facilitator.minimize(event);
    }
}
