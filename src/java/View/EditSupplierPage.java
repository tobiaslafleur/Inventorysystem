package View;

import Controller.Main;
import Model.Countries;
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
    @FXML private TextField street;
    @FXML private TextField city;
    @FXML private ComboBox<Countries> countries;
    @FXML private TextField email;
    @FXML private ComboBox<Supplier> suppliers;

    @FXML public void initialize() {
        this.facilitator = Main.getInstance().getFacilitator();

        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
        supplierList.addAll(facilitator.getSupplierList());
        suppliers.setItems(supplierList);

        ObservableList<Countries> countryList = FXCollections.observableArrayList();
        countryList.addAll(Countries.values());
        countries.setItems(countryList);
    }

    public void supplierSelection(ActionEvent event) {
        if(suppliers.getValue() != null) {
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

        facilitator.updateSupplier(name, phone, street, city, country, email, id);
        facilitator.updateSupplierTable();
        facilitator.closeSecondStage(e);
    }
    public void cancel(ActionEvent event) {
        facilitator.closeSecondStage(event);
    }
}
