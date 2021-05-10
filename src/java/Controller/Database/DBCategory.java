package Controller.Database;

import Controller.DBController;
import Model.Category;
import Model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<Category> getCategoryList() {
        ArrayList<Category> categoryList = new ArrayList<>();
        try {
            dbController.connect();
            Connection conn = dbController.getConnection();
            String query = "Select * from Category";

            PreparedStatement prep = null;
            prep = conn.prepareStatement(query);
            ResultSet rs = prep.executeQuery();

            while(rs.next()) {
                String name = rs.getString("name");
                Category category = new Category(name);
                categoryList.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
}
