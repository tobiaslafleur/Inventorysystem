package View;

import Controller.Controller;

public class GUIFacilitator {
    private Controller controller;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private ApplicationPage applicationPage;

    public GUIFacilitator(Controller controller) {
        this.controller = controller;
    }
    public GUIFacilitator(LoginPage loginPage) {
        this.loginPage = loginPage;
    }
    public GUIFacilitator(RegistrationPage registrationPage) {
        this.registrationPage = registrationPage;
    }
    public GUIFacilitator(ApplicationPage applicationPage) {
        this.applicationPage = applicationPage;
    }

    public void createUser(String username, String password, String emailAddress, String phoneNr, String address) {
       controller.createUser(username, password, emailAddress, phoneNr, address);

    }
}
