package Controller.Database;

import Controller.DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBProduct {
    private DBController dbController;

    public DBProduct(DBController dbController) {
        this.dbController = dbController;
    }

    public boolean addSupplier(String name, String phone, String address, String email) {
        try {
            dbController.connect();
            Connection conn = dbController.getConnection();

            String query =
                    "INSERT INTO Supplier(name, phone, address, email) " +
                            "VALUES(?, ?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, email);

            preparedStatement.execute();
            preparedStatement.close();
            conn.close();
            dbController.disconnect();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}
