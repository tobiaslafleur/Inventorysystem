package Controller;

import Model.User;

public class DatabaseController {
    private MainController mainController;

    public DatabaseController(MainController mainController) {
        this.mainController = mainController;
    }

    public boolean checkUser(User user) {
        if(user.getUsername().equals("inventory") && user.getPassword().equals("system")){
            user.setUserID(1);
            return true;
        } else {
            user.setUsername(null);
            user.setPassword(null);
            user.setUserID(-1);
            return false;
        }
    }
}
