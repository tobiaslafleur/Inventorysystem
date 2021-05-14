package Controller.Database;
import Controller.DBController;
import Model.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class facilitating communication with the database.
 */

public class DBProduct {
    private DBController dbController;
    private ArrayList<Product> productList;
    private ArrayList<Product> searchList;

    /**
     * Constructor instantiating dbController, productList and searchList
     * @param dbController is the DBController object to be instantiated into dbController.
     */
    public DBProduct(DBController dbController) {
        this.dbController = dbController;
        productList = new ArrayList<>();
        searchList = new ArrayList<>();
    }

    /**
     * Receives values and conveys them to the database to add a row into the product Table.
     * @param name is the name of the product.
     * @param stock is the amount on the product in stock.
     * @param price is the retail price of the product.
     * @param categoryID is the ID of the product category.
     * @param shelfPosition is the position in the warehouse the product occupies.
     * @param supplierID is the ID of the supplier of the product.
     * @param cost is the cost of the product.
     * @param userID is the ID of the user currently logged in.
     * @return returns a boolean value indicating the succcess of the opereation.
     */
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

    /**
     * creates an Arraylist of the products associated with the logged in user.
     */
    public void createProductTable() {
        try {
            dbController.connect();
            Connection conn = dbController.getConnection();
            String query = "Select * from ViewUserProductList WHERE user_id = ?";

            PreparedStatement prep = null;
            prep = conn.prepareStatement(query);
            prep.setInt(1, dbController.getUser().getUserID());
            ResultSet rs = prep.executeQuery();

            while(rs.next()) {
                int productID = rs.getInt("p_code");
                String name = rs.getString("name");
                int stock = Integer.parseInt(rs.getString("stock"));
                BigDecimal price = new BigDecimal(rs.getString("price"));
                String category = rs.getString("c_name");
                String shelfPosition = rs.getString("shelf_pos");
                String supplier = rs.getString("s_name");
                int supplierID = rs.getInt("s_id");
                BigDecimal cost = new BigDecimal(rs.getString("cost"));

                Product product = new Product(productID, name, stock, price, category, shelfPosition, supplier, supplierID, cost, dbController.getUser().getUserID());
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a list of products connected to the logged in user.
     * @return returns an arraylist of products.
     */
    public ArrayList<Product> getProductList() {
        if(!productList.isEmpty()) {
            productList.clear();
        }
        createProductTable();
        return productList;
    }

    /**
     * Returns a list of search results created by value of the incoming parameter.
     * @param searchString is the search term.
     * @return returns an Arraylist of products resulting from the search.
     */
    public ArrayList<Product> getProductSearch(String searchString) {
        searchList.clear();
        createSearchList(searchString);
        return searchList;
    }

    /**
     * Queries the database with a search term and populates an Arraylist with the resulting products.
     * @param searchString
     */
    private void createSearchList(String searchString) {
        try {
            dbController.connect();
            Connection conn = dbController.getConnection();
            int searchNumber = 0;
            String query;
            PreparedStatement prep = null;

            try {
                searchNumber = Integer.parseInt(searchString);
            } catch (Exception e) {}

            if(searchNumber > 0) {
                query = "Select * from ViewUserProductList WHERE p_code = ? OR s_id = ? AND [user_id] = ?";
                prep = conn.prepareStatement(query);
                prep.setInt(1, searchNumber);
                prep.setInt(2, searchNumber);
                prep.setInt(3, dbController.getUser().getUserID());

            } else {
                query = "Select * from ViewUserProductList WHERE " +
                        "([name] Like ? OR s_name LIKE ? OR shelf_pos LIKE ? " +
                        "OR c_name LIKE ?) AND [user_id] = ?";
                prep = conn.prepareStatement(query);
                prep.setString(1, "%" + searchString + "%");
                prep.setString(2, "%" + searchString + "%");
                prep.setString(3, "%" + searchString + "%");
                prep.setString(4, "%" + searchString + "%");
                prep.setInt(5, dbController.getUser().getUserID());
            }

            ResultSet rs = prep.executeQuery();
            while(rs.next()) {
                int productID = rs.getInt("p_code");
                String name = rs.getString("name");
                int stock = Integer.parseInt(rs.getString("stock"));
                BigDecimal price = new BigDecimal(rs.getString("price"));
                String category = rs.getString("c_name");
                String shelfPosition = rs.getString("shelf_pos");
                String supplier = rs.getString("s_name");
                int supplierID = rs.getInt("s_id");
                BigDecimal cost = new BigDecimal(rs.getString("cost"));

                Product product = new Product(productID, name, stock, price, category, shelfPosition, supplier, supplierID, cost, dbController.getUser().getUserID());
                searchList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a product from the database.
     * @param productID is the ID of the product to remove.
     */
    public void removeProduct(int productID) {
        try {
            dbController.connect();
            Connection conn = dbController.getConnection();

            String query =
                    "Delete From Product Where id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, productID);

            preparedStatement.execute();
            preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates an SQL query to update product information.
     */
    public void updateProductSetup(int id, String name, int quantity, int categoryID, BigDecimal price, String shelf, BigDecimal cost) {

        String query = " ";

        if(name != null) {
            query =
                    "UPDATE Product SET [name] = '" + name + "' Where id = " + id;
            executeUpdate(query);
        }
        if(quantity > 0) {
            query =
                    "UPDATE Product SET [stock] = " + quantity + " Where id = " + id;
            executeUpdate(query);
        }
        if(categoryID > 0) {
            query =
                    "UPDATE Product SET [category_id] = " + categoryID + " Where id = " + id;
            executeUpdate(query);
        }
        if(price != null) {
            query =
                    "UPDATE Product SET [price] = '" + price + "' Where id = " + id;
            executeUpdate(query);
        }

        if(shelf != null) {
            query =
                    "UPDATE Product SET [shelf_pos] = '" + shelf + "' Where id = " + id;
            executeUpdate(query);
        }
        if(cost != null) {
            query =
                    "UPDATE Product SET [cost] = '" + cost + "' Where id = " + id;
            executeUpdate(query);
        }
    }

    /**
     * Executes the query created in updateProductSetup()
     * @param query is the query to execute.
     */
    private void executeUpdate(String query) {
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
