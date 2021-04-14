package Controller.Database;

import Controller.DBController;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBProduct {
    private DBController dbController;

    public DBProduct(DBController dbController) {
        this.dbController = dbController;
    }

    public boolean addProduct(String name, int stock, BigDecimal price, int categoryID, String shelfPosition, int supplierID, BigDecimal cost, int userID) {
        try {
            dbController.connect();
            Connection conn = dbController.getConnection();

            String query =
                    "INSERT INTO Product(name, stock, price, category_id, shelf_pos, supplier_id, cost, user_id) " +
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, stock);
            preparedStatement.setBigDecimal(3, price);
            preparedStatement.setInt(4, categoryID);
            preparedStatement.setString(5, shelfPosition);
            preparedStatement.setInt(6, supplierID);
            preparedStatement.setBigDecimal(7, cost);
            preparedStatement.setInt(8, userID);

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
