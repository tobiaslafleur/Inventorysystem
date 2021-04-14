package Tests;

import Controller.Controller;

public class UserLoginTest {
    public static void main(String[] args) {
        Controller controller = new Controller();
        if(controller.checkUser("hej", "hej")) {
            System.out.println("Login test successful");
        }

    }
}
