package Controller.Database;

import Controller.DBController;
import Model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBCategory {
    private DBController dbController;

    public DBCategory(DBController dbController){
        this.dbController = dbController;
    }

    public boolean createCategory(Category category) {
        try {
            dbController.connect();
            Connection conn = dbController.getConnection();

            String query =
                    "INSERT INTO Category(name) " + "VALUES(?)";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, category.getName());

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
