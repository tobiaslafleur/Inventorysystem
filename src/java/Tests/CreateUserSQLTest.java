package Tests;

import Controller.Controller;
import Controller.DBController;
import Controller.Database.DBUser;
import Model.User;

public class CreateUserSQLTest {
    public static void main(String[] args) {
        DBUser dbUser = new DBUser(new DBController(new Controller()));
        User user = new User("Orenthal James", "murderer", "pang pang", "555-55-55", "Why not in jail");
        if(dbUser.createUser(user)) {
            System.out.println("Test successful");
        }
    }
}
