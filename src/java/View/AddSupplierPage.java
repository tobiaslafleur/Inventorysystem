package View;

import Controller.ErrorHandling.SupplierErrorHandling;
import Controller.Main;
import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.swing.*;

public class AddSupplierPage {
    private static AddSupplierPage instance;
    private GUIFacilitator facilitator;

    @FXML private TextField name;
    @FXML private TextField phone;
    @FXML private TextField street;
    @FXML private TextField city;
//    @FXML private TextField country;
    @FXML private TextField email;
    @FXML private ComboBox<Countries> countries;

    /**
     * Intializes the connection between GUIFacilitator and this class.
     */
    @FXML public void initialize() {
        instance = this;
        facilitator = Main.getInstance().getFacilitator();
//        setInstance();

        ObservableList<Countries> countryList = FXCollections.observableArrayList();
        countryList.addAll(Countries.values());
        countries.setItems(countryList);
    }

    /**
     * Intializes the connection between GUIFacilitator and this class.
     */
//    private void setInstance() {
//        facilitator.setAddSupplierInstance(instance);
//    }

    /**
     * Adds the supplier to the database.
     * @param event     The event that triggers the method.
     */
    public void addSupplier(ActionEvent event) {
        String supName = name.getText();
        String supPhone = phone.getText();
        String supStreet = street.getText();
        String supCity = city.getText();
        String country = countries.getValue().name();
        String supEmail = email.getText();

        boolean allOK;
        try {
            allOK = SupplierErrorHandling.errorHandling(supName, supEmail, supPhone, supStreet, supCity, country);
        } catch (Exception e) {
            allOK = false;
        }

        if(allOK) {
            facilitator.addSupplier(supName, supPhone, supStreet, supCity, country, supEmail);
            facilitator.updateSupplierTable();
            facilitator.closeSecondStage(event);
        } else {
            JOptionPane.showMessageDialog(null, "Some of them fields wrong");
        }

//        if(facilitator.addSupplier(supName, supPhone, supStreet, supCity, country, supEmail)) {
//            facilitator.updateSupplierTable();
//            facilitator.closeSecondStage(event);
//        } else {
//            //TODO: Label saying: "Failed to add supplier."
//            //temp:
//
//        }
    }

    /**
     * Cancels the operation and returns to ApplicationPage.
     * @param event The event that triggers the method.
     */
    public void cancel(ActionEvent event) {
        facilitator.closeSecondStage(event);
    }
}
