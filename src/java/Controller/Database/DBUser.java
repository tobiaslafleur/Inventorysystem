package Controller.Database;

import Controller.DBController;
import Model.User;

public class DBUser {
    private DBController dbController;

    public DBUser(DBController dbController) {
        this.dbController = dbController;
    }

    public void createUser(User user) {
        System.out.println(user);
    }
}
