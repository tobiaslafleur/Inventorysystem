package Controller.Database;

import Controller.DBController;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUser {
    private DBController dbController;

    public DBUser(DBController dbController) {
        this.dbController = dbController;
    }

    public boolean createUser(User user) {
        try {
            dbController.connect();
            Connection conn = dbController.getConnection();

            String query =
                    "INSERT INTO [User](username, password, email, address, phone) " +
                    "VALUES(?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmailAddress());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getPhone());

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

    public boolean checkUser(String username, String password){

        try{
            dbController.connect();
            Connection conn = dbController.getConnection();

            String query =
                    "SELECT * FROM [User]";
                    /*"WHERE username = ?" +
                    "AND password = ?";*/

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                if(rs.getString(2).toLowerCase().equals(username) && rs.getString(3).equals(password)){

                    User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6));

                    dbController.setUser(user);
                    return true;
                }
            }
            preparedStatement.close();
            conn.close();
            dbController.disconnect();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
