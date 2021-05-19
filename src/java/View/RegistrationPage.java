package View;

import Controller.ErrorHandling.RegistrationPageHandling;
import Controller.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Arrays;

public class RegistrationPage {
    private static RegistrationPage instance;
    private GUIFacilitator facilitator;

    private Stage stage;

    @FXML private AnchorPane anchorPane;
    @FXML private AnchorPane dragAnchor;
    @FXML private Hyperlink cancel;

    @FXML private Label createAccount;
    @FXML private TextField username;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private TextField address;
    @FXML private PasswordField password;
    @FXML private PasswordField repeatedPW;
    @FXML private Button registerBtn;

    @FXML private Label lblUsernameWarning;
    @FXML private Label lblPhoneWarning;
    @FXML private Label lblEmailWarning;
    @FXML private Label lblAddressWarning;
    @FXML private Label lblPwWarning;
    @FXML private Label lblRepeatWarning;

    @FXML private Button btnCheck;

    @FXML private ComboBox<String> cmbAreaCodes;

    @FXML private Label lblEnterAllFields;

    private double x = 0, y = 0;

    @FXML public void initialize() {
        instance = this;
        facilitator = Main.getInstance().getFacilitator();
        setInstance();
        fixFocus();
        dragAnchor();

        lblUsernameWarning.setText("");
        lblPhoneWarning.setText("");
        lblEmailWarning.setText("");
        lblAddressWarning.setText("");
        lblPwWarning.setText("");
        lblRepeatWarning.setText("");
        lblEnterAllFields.setVisible(false);

        cmbAreaCodes.getItems().addAll("+46");
        cmbAreaCodes.setId("cmb-area");

        btnCheck.setId("check-btn");
        btnCheck.setOnAction(actionEvent -> checkUsername());

        username.setOnKeyTyped(actionEvent -> {
            if(username.getText().isEmpty()) {
                lblUsernameWarning.setText(Language.getRegErrUsernameOne());
                username.setStyle("-fx-border-color: #EB5D5D;");
            } else {
                username.setStyle("-fx-border-color: #FFFF3A;");
                lblUsernameWarning.setText(Language.getRegErrUsernameTwo());
            }
        });

        email.setOnKeyTyped(actionEvent -> {
            if(RegistrationPageHandling.isEmailValid(email.getText())){
                email.setStyle("-fx-border-color: #8EFF8B;");
                lblEmailWarning.setText("");
            } else {
                email.setStyle("-fx-border-color: #EB5D5D;");
                lblEmailWarning.setText(Language.getRegErrEmail());
            }
        });

        phone.setOnKeyTyped(actionEvent -> {
            if(RegistrationPageHandling.isPhoneValid(phone.getText()) && !(cmbAreaCodes.getValue() == null)){
                phone.setStyle("-fx-border-color: #8EFF8B;");
                lblPhoneWarning.setText("");
            } else {
                phone.setStyle("-fx-border-color: #EB5D5D;");
                lblPhoneWarning.setText(Language.getRegErrPhone());
            }
        });

        address.setOnKeyTyped(actionEvent -> {
            if(!address.getText().isEmpty()){
                address.setStyle("-fx-border-color: #8EFF8B;");
                lblAddressWarning.setText("");
            } else {
                address.setStyle("-fx-border-color: #EB5D5D;");
                lblAddressWarning.setText(Language.getRegErrAddress());
            }
        });

        password.setOnKeyTyped(actionEvent -> {
            if(RegistrationPageHandling.isPasswordValid(password.getText())){
                password.setStyle("-fx-border-color: #8EFF8B;");
                lblPwWarning.setText("");
            } else {
                password.setStyle("-fx-border-color: #EB5D5D;");
                lblPwWarning.setText(Language.getRegErrPassword());
            }

            if(RegistrationPageHandling.isRepeatValid(password.getText(), repeatedPW.getText())){
                repeatedPW.setStyle("-fx-border-color: #8EFF8B;");
                lblRepeatWarning.setText("");
            } else {
                repeatedPW.setStyle("-fx-border-color: #EB5D5D;");
                lblRepeatWarning.setText(Language.getRegErrRepeatPassword());
            }
        });

        repeatedPW.setOnKeyTyped(actionEvent -> {
            if(RegistrationPageHandling.isRepeatValid(password.getText(), repeatedPW.getText())){
                repeatedPW.setStyle("-fx-border-color: #8EFF8B;");
                lblRepeatWarning.setText("");
            } else {
                repeatedPW.setStyle("-fx-border-color: #EB5D5D;");
                lblRepeatWarning.setText(Language.getRegErrRepeatPassword());
            }
        });

        setLanguage();
    }

    @FXML private void anchorPaneClicked() {
        fixFocus();
    }

    private void fixFocus() {
        Platform.runLater(() -> anchorPane.requestFocus());
    }

    private void dragAnchor() {
        dragAnchor.setOnMousePressed(((event) -> {
            fixFocus();
            x = event.getX();
            y = event.getY();
        }));

        dragAnchor.setOnMouseDragged(((event) -> {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        }));
    }

    private void setInstance() {
        facilitator.setRegistrationInstance(instance);
    }

    private void checkUsername(){
        if(RegistrationPageHandling.usernameExists(username.getText(), facilitator)){
            username.setStyle("-fx-border-color: #8EFF8B;");
            lblUsernameWarning.setText("");
        } else {
            username.setStyle("-fx-border-color: #EB5D5D;");
            if(username.getText().isEmpty()) {
                lblUsernameWarning.setText(Language.getRegErrUsernameOne());
            } else {
                lblUsernameWarning.setText(Language.getRegErrUsernameThree());
            }
        }
    }

    public void register(javafx.event.ActionEvent event) {
        boolean allOk = RegistrationPageHandling.errorHandling(username.getText(), email.getText(), phone.getText(), cmbAreaCodes.getValue(), password.getText(), repeatedPW.getText(), facilitator);
        checkUsername();

        //If no warnings, create account
        if(allOk){
            String areacode = cmbAreaCodes.getValue();
            String number;
            if(phone.getText().toCharArray()[0] == '0'){
                char[] temp = Arrays.copyOfRange(phone.getText().toCharArray(), 1, phone.getText().length());

                StringBuilder strBuilder = new StringBuilder();
                for(char c : temp) {
                    strBuilder.append(c);
                }
                String str = strBuilder.toString();
                number = areacode + str;

            } else {
                number = areacode + phone.getText();
            }
            System.out.println(number);
            facilitator.changeWindow(event, "/fxml/LoginPage.fxml");
            facilitator.createUser(username.getText(), password.getText(), email.getText(), number, address.getText());
        } else {
            if(!RegistrationPageHandling.isAreaCodeValid(cmbAreaCodes.getValue())) {
                cmbAreaCodes.setStyle("-fx-background-color: #EB5D5D, #EB5D5D, #EB5D5D, #EB5D5D;");
            }
            lblEnterAllFields.setVisible(true);
        }
    }
    public void cancel(ActionEvent accountRegisterCancelled) {
        facilitator.changeWindow(accountRegisterCancelled, "/fxml/LoginPage.fxml");
    }
    public void close(ActionEvent event) {
        facilitator.close(event);
    }
    public void minimize(ActionEvent event) {
        facilitator.minimize(event);
    }

    public void setLanguage() {
        cancel.setText(Language.getRegCancel());
        createAccount.setText(Language.getRegCreateAccount());
        username.setPromptText(Language.getRegUsername());
        btnCheck.setText(Language.getRegCheckAvailability());
        email.setPromptText(Language.getRegEmail());
        phone.setPromptText(Language.getRegPhone());
        address.setPromptText(Language.getRegAddress());
        password.setPromptText(Language.getRegPassword());
        repeatedPW.setPromptText(Language.getRegRepeatPassword());
        registerBtn.setText(Language.getRegRegisterAccountBtn());
    }
}
