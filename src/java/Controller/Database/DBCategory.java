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

    public boolean createCategory(String categoryName) {
        try {
            dbController.connect();
            Connection conn = dbController.getConnection();

            String query =
                    "EXEC ecinvDB.dbo.ProcAddCategory ?, ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, categoryName);
            preparedStatement.setInt(2, dbController.getUser().getUserID());

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
            String query = "Select * from ViewCategory where user_id = ?";

            PreparedStatement prep = null;
            prep = conn.prepareStatement(query);
            prep.setInt(1, dbController.getUser().getUserID());
            ResultSet rs = prep.executeQuery();

            while(rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("category_id");
                Category category = new Category(name, id);
                categoryList.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
}
