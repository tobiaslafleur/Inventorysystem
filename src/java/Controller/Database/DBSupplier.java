package Controller.Database;

import Controller.DBController;
import Model.Supplier;

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
}
