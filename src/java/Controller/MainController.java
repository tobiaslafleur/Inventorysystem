package Controller;

import View.*;

public class MainController {
    private DatabaseController dbController;

    public MainController(){
        dbController = new DatabaseController(this);
    }

}
