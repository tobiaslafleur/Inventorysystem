package Controller.Database;

import Controller.DBController;
import Model.Supplier;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBSupplier {
    private DBController dbController;

    public DBSupplier(DBController dbController) {
        this.dbController = dbController;
    }

    public boolean addSupplier(Supplier supplier) {
        try {
            dbController.connect();
            Connection conn = dbController.getConnection();

            String query =
                    "INSERT INTO Supplier(name, phone, address, email) " +
                            "VALUES(?, ?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getPhone());
            preparedStatement.setString(3, supplier.getAddress());
            preparedStatement.setString(4, supplier.getEmail());

            preparedStatement.execute();
            preparedStatement.close();
            conn.close();
            dbController.disconnect();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public void updateSupplierSetup(Supplier supplierUpdate) {
            String name = supplierUpdate.getName();
            String phone = supplierUpdate.getPhone();
            String address = supplierUpdate.getAddress();
            String email = supplierUpdate.getEmail();
            int id = supplierUpdate.getId();

            String query = " ";

        if(name != null) {
            query =
                    "UPDATE Supplier SET [name] = '" + name + "' Where id = " + id;
            executeUpdate(query);
        }

        if(phone != null) {
            query =
                    "UPDATE Supplier SET [phone] = '" + phone + "' Where id = " + id;
            executeUpdate(query);
        }
        if(address != null) {
            query =
                    "UPDATE Supplier SET [address] = '" + address + "' Where id = " + id;
            executeUpdate(query);
        }
        if(email != null) {
            query =
                    "UPDATE Supplier SET [email] = '" + email + "' Where id = " + id;
            executeUpdate(query);
        }

    }

    public void executeUpdate(String query) {
        try {
            dbController.connect();
            Connection conn = dbController.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.execute();
            preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
