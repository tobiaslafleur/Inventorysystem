package Tests;

import Controller.Controller;
import Controller.DBController;
import Controller.Database.DBUser;
import Model.User;

public class CreateUserTest {
    public static void main(String[] args) {
        DBUser dbUser = new DBUser(new DBController(new Controller()));
        User user = new User("username", "password", "email", "phone", "address");
        if(dbUser.createUser(user)) {
            System.out.println("Create User test successful");
        }
    }
}
