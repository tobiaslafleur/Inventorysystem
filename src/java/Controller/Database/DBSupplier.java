package Controller.Database;

import Controller.DBController;
import Model.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBSupplier {
    private DBController dbController;
    private ArrayList<Supplier> searchList;

    public DBSupplier(DBController dbController) {
        this.dbController = dbController;
        this.searchList = new ArrayList<>();
    }

    public ArrayList<Supplier> getSupplierList() {
        ArrayList<Supplier> supplierList = new ArrayList<>();
        try {
            dbController.connect();
            Connection conn = dbController.getConnection();
            String query = "Select [name], phone, street, [city], country, email, supplier_id  from ViewSupplier " +
                    "where [user_id] = ?";

            PreparedStatement prep = null;
            prep = conn.prepareStatement(query);
            prep.setInt(1, dbController.getUser().getUserID());
            ResultSet rs = prep.executeQuery();

            while(rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String country = rs.getString("country");
                String email = rs.getString("email");
                int supplierID = rs.getInt("supplier_id");

                Supplier supplier = new Supplier(name, phone, street, city, country, email, supplierID);
                supplierList.add(supplier);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplierList;
    }

    public boolean addSupplier(Supplier supplier) {
        try {
            dbController.connect();
            Connection conn = dbController.getConnection();

            String query =
                    "EXEC ecinvDB.dbo.ProcAddSupplier ?, ?, ?, ?, ?, ?, ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getPhone());
            preparedStatement.setString(3, supplier.getStreet());
            preparedStatement.setString(4, supplier.getCity());
            preparedStatement.setString(5, supplier.getCountry());
            preparedStatement.setString(6, supplier.getEmail());
            preparedStatement.setInt(7, dbController.getUser().getUserID());

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
            String street = supplierUpdate.getStreet();
            String city = supplierUpdate.getCity();
            String country = supplierUpdate.getCountry();
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
        if(street != null) {
            query =
                    "UPDATE Supplier SET [street] = '" + street + "' Where id = " + id;
            executeUpdate(query);
        }
        if(city != null) {
            query =
                    "UPDATE Supplier SET [city] = '" + city + "' Where id = " + id;
            executeUpdate(query);
        }
        if(country != null) {
            query =
                    "UPDATE Supplier SET [country] = '" + country + "' Where id = " + id;
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

    public ArrayList<Supplier> getSupplierSearch(String searchText) {
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

            if(searchNumber > 0 && searchNumber < 100000) {
                query = "Select * from ViewSupplier WHERE supplier_id = ? AND [user_id] = ?";
                prep = conn.prepareStatement(query);
                prep.setInt(1, searchNumber);
                prep.setInt(2, dbController.getUser().getUserID());

            } else {
                query = "Select * from ViewSupplier WHERE " +
                        "([name] Like ? OR phone LIKE ? OR street LIKE ? " +
                        "OR city LIKE ? OR country LIKE ? OR email LIKE ?) AND [user_id] = ?";
                prep = conn.prepareStatement(query);
                prep.setString(1, "%" + searchText + "%");
                prep.setString(2, "%" + searchText + "%");
                prep.setString(3, "%" + searchText + "%");
                prep.setString(4, "%" + searchText + "%");
                prep.setString(5, "%" + searchText + "%");
                prep.setString(6, "%" + searchText + "%");
                prep.setInt(7, dbController.getUser().getUserID());
            }

            ResultSet rs = prep.executeQuery();
            while(rs.next()) {
                int supplierID = rs.getInt("supplier_id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String country = rs.getString("country");
                String email = rs.getString("email");

                Supplier supplier = new Supplier(name, phone, street, city, country, email, supplierID);
                searchList.add(supplier);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
