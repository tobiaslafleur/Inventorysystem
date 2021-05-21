package View;

import Controller.ErrorHandling.SupplierErrorHandling;
import Controller.Main;
import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddSupplierPage {
//    private static AddSupplierPage instance;
    private GUIFacilitator facilitator;
    @FXML private TextField name;
    @FXML private TextField phone;
    @FXML private TextField street;
    @FXML private TextField city;
    @FXML private TextField email;
    @FXML private ComboBox<Countries> countries;

    @FXML private Label lblTitle;
    @FXML private Label lblName;
    @FXML private Label lblPhone;
    @FXML private Label lblStreet;
    @FXML private Label lblCity;
    @FXML private Label lblEmail;

    /**
     * Intializes the connection between GUIFacilitator and this class.
     */
    @FXML public void initialize() {
//        instance = this;
//        setInstance();
        facilitator = Main.getInstance().getFacilitator();

        ObservableList<Countries> countryList = FXCollections.observableArrayList();
        countryList.addAll(Countries.values());
        countries.setItems(countryList);

        lblTitle.setText(Language.getSupTitle());
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
        String supName = "";
        if(!name.getText().equals("")) {
            supName = name.getText();
        }
        String supPhone = "";
        if(!phone.getText().equals("")) {
            supPhone = phone.getText();
        }
        String supStreet = "";
        if(!street.getText().equals("")) {
            supStreet = street.getText();
        }
        String supCity = "";
        if(!city.getText().equals("")) {
            supCity = city.getText();
        }
        String country = "";
        if(countries.getValue() != null) {
            country = countries.getValue().name();
        }
        String supEmail = "";
        if(!email.getText().equals("")) {
            supEmail = email.getText();
        }

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
            if(SupplierErrorHandling.isNameValid(supName)) {
                name.setStyle("-fx-border-color: #1F701D;");
            } else {
                name.setStyle("-fx-border-color: #974F4F;");
            }

            if(SupplierErrorHandling.isPhoneValid(supPhone)) {
                phone.setStyle("-fx-border-color: #1F701D;");
            } else {
                phone.setStyle("-fx-border-color: #974F4F;");
            }

            if(SupplierErrorHandling.isStreetValid(supStreet)) {
                street.setStyle("-fx-border-color: #1F701D;");
            } else {
                street.setStyle("-fx-border-color: #974F4F;");
            }

            if(SupplierErrorHandling.isCityValid(supCity)) {
                city.setStyle("-fx-border-color: #1F701D;");
            } else {
                city.setStyle("-fx-border-color: #974F4F;");
            }

            if(countries.getValue() != null) {
                countries.setStyle("-fx-border-color: #1F701D;");
            } else {
                countries.setStyle("-fx-border-color: #974F4F;");
            }

            if(SupplierErrorHandling.isEmailValid(supEmail)) {
                email.setStyle("-fx-border-color: #1F701D;");
            } else {
                email.setStyle("-fx-border-color: #974F4F;");
            }
        }

    }

    /**
     * Cancels the operation and returns to ApplicationPage.
     * @param event The event that triggers the method.
     */
    public void cancel(ActionEvent event) {
        facilitator.closeSecondStage(event);
    }
}
