package Controller.Database;

import Controller.DBController;
import Model.Category;
import Model.Product;
import Model.Supplier;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBCategory {
    private DBController dbController;
    private ArrayList<Category> searchList;

    public DBCategory(DBController dbController){
        this.dbController = dbController;
        searchList = new ArrayList<>();
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

    public ArrayList<Category> getCategorySearch(String searchText) {
        searchList.clear();
        createSearchList(searchText);
        return searchList;
    }

    private void createSearchList(String searchText) {
        try {
            dbController.connect();
            Connection conn = dbController.getConnection();
            int searchNumber = 0;
            String query;
            PreparedStatement prep = null;

            try {
                searchNumber = Integer.parseInt(searchText);
            } catch (Exception e) {}

            if(searchNumber > 0) {
                query = "Select * from ViewCategory WHERE category_id = ? AND [user_id] = ?";
                prep = conn.prepareStatement(query);
                prep.setInt(1, searchNumber);
                prep.setInt(2, dbController.getUser().getUserID());

            } else {
                query = "Select * from ViewCategory WHERE " +
                        "[name] Like ? AND [user_id] = ?";
                prep = conn.prepareStatement(query);
                prep.setString(1, "%" + searchText + "%");
                prep.setInt(2, dbController.getUser().getUserID());
            }

            ResultSet rs = prep.executeQuery();
            while(rs.next()) {
                int categoryID = rs.getInt("category_id");
                String name = rs.getString("name");

                Category category = new Category(name, categoryID);
                searchList.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
