package Controller;

import Model.*;
import View.*;

public class MainController {
    private TempMainFrame mainFrame;

    public MainController(){
        mainFrame = new TempMainFrame(this);
    }

    public void setUser(String username, char[] password) {
        User user = new User(username, String.valueOf(password));

        System.out.println(user);

        /*-------------------------------------------------------------------------------------*/

        //How it should work (temporarily)
        /*if(dbController.checkUser(user)) {
            //proceed with login
            //sets userID in dbController.checkUser();
            mainFrame.updateLogin();
        } else {
            user.setUsername(null);
            user.setPassword(null);
            user.setUserID(-1);
            mainFrame.showMsg("Wrong login");
        }*/

        /*-------------------------------------------------------------------------------------*/

        /*Temp login*/
        if(user.getUsername().equals("inventory") && user.getPassword().equals("system")){
            user.setUserID(1);
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
