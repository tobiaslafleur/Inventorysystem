package Controller.Database;

import Controller.DBController;
import Model.Product;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBProduct {
    private DBController dbController;
    private ArrayList<Product> productList;

    public DBProduct(DBController dbController) {
        this.dbController = dbController;
        productList = new ArrayList<>();
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

    public void createProductTable(int userID) {
        try {
            dbController.connect();
            Connection conn = dbController.getConnection();

            String query = "Select  P.name, P.stock, P.price, C.name AS c_name, P.shelf_pos, S.name AS s_name, P.cost " +
                    "FROM Product P JOIN Supplier S ON P.supplier_id = S.id JOIN Category C ON C.id = P.category_id " +
                    "WHERE P.user_id = " + userID;

            PreparedStatement prep = null;
            prep = conn.prepareStatement(query);
            ResultSet rs = prep.executeQuery();

            while(rs.next()) {
                String name = rs.getString("name");
                int stock = Integer.parseInt(rs.getString("stock"));
                BigDecimal price = new BigDecimal(rs.getString("price"));
                String category = rs.getString("c_name");
                String shelfPosition = rs.getString("shelf_pos");
                String supplier = rs.getString("s_name");
                BigDecimal cost = new BigDecimal(rs.getString("cost"));

                Product product = new Product(name, stock, price, category, shelfPosition, supplier, cost, userID);
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> getProductList() {
        if(!productList.isEmpty()) {
            productList.clear();
            createProductTable(dbController.getUser().getUserID());
        }
        return productList;
    }
}
