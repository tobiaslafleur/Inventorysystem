package Controller;

import Model.*;
import View.*;

public class MainController {
    private TempMainFrame mainFrame;
    private DatabaseController dbController;

    public MainController(){
        mainFrame = new TempMainFrame(this);
        dbController = new DatabaseController(this);
    }

    public void setUser(String username, char[] password) {
        User user = new User(username, String.valueOf(password));

        System.out.println(user);

        /*-------------------------------------------------------------------------------------*/

        //How it should work (temporarily)
        if(dbController.checkUser(user)) {
            mainFrame.updateLogin();
        } else {
            user.setUsername(null);
            user.setPassword(null);
            user.setUserID(-1);
            mainFrame.showMsg("Wrong login");
        }

        /*-------------------------------------------------------------------------------------*/

        System.out.println(user);
    }
}
