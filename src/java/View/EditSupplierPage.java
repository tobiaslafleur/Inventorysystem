package View;

import Controller.ErrorHandling.SupplierErrorHandling;
import Controller.Main;
import Model.Countries;
import Model.Product;
import Model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;

public class EditSupplierPage {
    private GUIFacilitator facilitator;
    @FXML private TextField name;
    @FXML private TextField phone;
    @FXML private TextField street;
    @FXML private TextField city;
    @FXML private ComboBox<Countries> countries;
    @FXML private TextField email;
    @FXML private ComboBox<Supplier> suppliers;

    @FXML private Label lblSupplier;
    @FXML private Label lblName;
    @FXML private Label lblPhone;
    @FXML private Label lblStreet;
    @FXML private Label lblCity;
    @FXML private Label lblEmail;

    @FXML public void initialize() {
        this.facilitator = Main.getInstance().getFacilitator();

        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
        supplierList.addAll(facilitator.getSupplierList());
        suppliers.setItems(supplierList);

        ObservableList<Countries> countryList = FXCollections.observableArrayList();
        countryList.addAll(Countries.values());
        countries.setItems(countryList);

        errorHandling();
    }

    public void errorHandling() {
        name.setOnKeyTyped(event -> {
            if(name.getText().isEmpty()) {
                name.setStyle("-fx-border-color: #974F4F;");
                lblName.setText(Language.getSupErrName());
            } else {
                name.setStyle("-fx-border-color: #1F701D;");
                lblName.setText("");
            }
        });

        phone.setOnKeyTyped(event -> {
            if(SupplierErrorHandling.isPhoneValid(phone.getText())){
                phone.setStyle("-fx-border-color: #1F701D;");
                lblPhone.setText("");
            } else {
                phone.setStyle("-fx-border-color: #974F4F;");
                lblPhone.setText(Language.getSupErrPhone());
            }
        });

        street.setOnKeyTyped(event -> {
            if(SupplierErrorHandling.isStreetValid(street.getText())){
                street.setStyle("-fx-border-color: #1F701D;");
                lblStreet.setText("");
            } else {
                street.setStyle("-fx-border-color: #974F4F;");
                lblStreet.setText(Language.getSupErrStreet());
            }
        });

        city.setOnKeyTyped(event -> {
            if(SupplierErrorHandling.isCityValid(city.getText())){
                city.setStyle("-fx-border-color: #1F701D;");
                lblCity.setText("");
            } else {
                city.setStyle("-fx-border-color: #974F4F;");
                lblCity.setText(Language.getSupErrCity());
            }
        });

        email.setOnKeyTyped(event -> {
            if(SupplierErrorHandling.isEmailValid(email.getText())){
                email.setStyle("-fx-border-color: #1F701D;");
                lblEmail.setText("");
            } else {
                email.setStyle("-fx-border-color: #974F4F;");
                lblEmail.setText(Language.getRegErrEmail());
            }
        });

        countries.setOnAction(event -> {
            countries.setStyle("-fx-border-color: #1F701D;");
        });
    }

    public void supplierSelection(ActionEvent event) {
        if(suppliers.getValue() != null) {
            lblSupplier.setText("");
            suppliers.setStyle("-fx-border-color: #1F701D;");

            name.setText(suppliers.getValue().getName());
            phone.setText(suppliers.getValue().getPhone());
            street.setText(suppliers.getValue().getStreet());
            city.setText(suppliers.getValue().getCity());
            email.setText(suppliers.getValue().getEmail());
            Countries country = Countries.valueOf(suppliers.getValue().getCountry());
            countries.setValue(country);

        }
    }

    public void updateSupplier(ActionEvent e) {
        if(suppliers.getValue() == null) {
            lblSupplier.setText(Language.getSupErrSup());
            suppliers.setStyle("-fx-border-color: #974F4F;");
        }

        String name = null;
        String phone = null;
        String street = null;
        String city = null;
        String country = null;
        String email = null;
        int id = 0;


        if(!this.name.getText().equals("")) {
            name = this.name.getText();
        }
        if(!this.phone.getText().equals("")) {
            phone = this.phone.getText();
        }
        if(!this.street.getText().equals("")) {
            street = this.street.getText();
        }
        if(!this.city.getText().equals("")) {
            city = this.city.getText();
        }
        if(countries.getValue() != null) {
            country = countries.getValue().name();
        }
        if(!this.email.getText().equals("")) {
            email = this.email.getText();
        }
        if(suppliers.getValue() != null) {
            id = suppliers.getValue().getId();
        }

        boolean allOK;
        try {
            allOK = SupplierErrorHandling.updateErrorHandling(name, phone, street, city, country, email, id);
        } catch (Exception exception) {
            allOK = false;
        }

        if(allOK) {
            facilitator.updateSupplier(name, phone, street, city, country, email, id);
            facilitator.updateSupplierTable();
            facilitator.closeSecondStage(e);
        } else {
            if(SupplierErrorHandling.isNameValid(name)) {
                this.name.setStyle("-fx-border-color: #1F701D;");
            } else {
                this.name.setStyle("-fx-border-color: #974F4F;");
            }

            if(SupplierErrorHandling.isPhoneValid(phone)) {
                this.phone.setStyle("-fx-border-color: #1F701D;");
            } else {
                this.phone.setStyle("-fx-border-color: #974F4F;");
            }

            if(SupplierErrorHandling.isStreetValid(street)) {
                this.street.setStyle("-fx-border-color: #1F701D;");
            } else {
                this.street.setStyle("-fx-border-color: #974F4F;");
            }

            if(SupplierErrorHandling.isCityValid(city)) {
                this.city.setStyle("-fx-border-color: #1F701D;");
            } else {
                this.city.setStyle("-fx-border-color: #974F4F;");
            }

            if(countries.getValue() != null) {
                countries.setStyle("-fx-border-color: #1F701D;");
            } else {
                countries.setStyle("-fx-border-color: #974F4F;");
            }

            if(SupplierErrorHandling.isEmailValid(email)) {
                this.email.setStyle("-fx-border-color: #1F701D;");
            } else {
                this.email.setStyle("-fx-border-color: #974F4F;");
            }
        }
    }
    public void cancel(ActionEvent event) {
        facilitator.closeSecondStage(event);
    }
}
