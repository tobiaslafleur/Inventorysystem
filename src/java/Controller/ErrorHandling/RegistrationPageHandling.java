package Controller.ErrorHandling;

import View.GUIFacilitator;

import java.util.ArrayList;

public class RegistrationPageHandling {

    private static boolean passwordOk = true;
    private static boolean usernameOk = true;
    private static boolean addressOk = true;
    private static boolean emailOk = true;
    private static boolean repeatPwOk = true;
    private static boolean phoneOk = true;

    private static final int PASSWORD_MIN_LENGTH = 8;
    private static ArrayList<String> warnings = new ArrayList<>();


    public static ArrayList<String> errorHandling(String username, String email, String phone, String address, String password, String repeated, GUIFacilitator facilitator) {

        if(!warnings.isEmpty()) {
            warnings.clear();
        }

        //username
        if(!isUsernameValid(username, facilitator)){
            warnings.add("Username already in use");
            usernameOk = false;
        } else if(username.isEmpty()) {
            warnings.add("Enter a username");
            usernameOk = false;
        } else {
            usernameOk = true;
        }

        //check password
        if(!isPasswordValid(password)) {
            warnings.add("Password must be at least 8 characters long \n and contain at least one uppercase letter and one number");
            passwordOk = false;
        } else {
            passwordOk = true;
        }

        //check email
        if(!isEmailValid(email)){
            warnings.add("Enter a valid email");
            emailOk = false;
        } else {
            emailOk = true;
        }

        if(!isRepeatValid(password, repeated)) {
            warnings.add("Passwords must match");
            repeatPwOk = false;
        } else {
            repeatPwOk = true;
        }

        if(!isPhoneValid(phone)) {
            warnings.add("Enter a valid number");
            phoneOk = false;
        } else {
            phoneOk = true;
        }

        if(warnings.isEmpty()){
            return null;
        } else {
            return warnings;
        }
    }

    public static boolean isPhoneValid(String phone) {
        try{
            if(Integer.parseInt(phone) > 10000000) {
                return true;
            } else {
                warnings.add("Enter a valid number");
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isRepeatValid(String password, String repeated) {
        return repeated.equals(password);
    }

    public static boolean isEmailValid(String email) {
        return email.contains("@");
    }

    public static boolean isUsernameValid(String username, GUIFacilitator facilitator){
        if(!usernameExists(username, facilitator)){
            warnings.add("Username already in use");
            return false;
        } else {
            return true;
        }
    }

    public static boolean isPasswordValid(String password) {
        if(password.length() < PASSWORD_MIN_LENGTH) {
            return false;
        }

        int charCount = 0;
        int numCount = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if(ch >= '0' && ch <= '9') {
                numCount++;
            }
            else if(ch >= 'A' && ch <= 'Z') {
                charCount++;
            }
        }

        return charCount >= 1 && numCount >= 1;
    }

    public static boolean usernameExists(String username, GUIFacilitator facilitator) {
        //TODO: Implement check username function
        if(username.isEmpty()) {
            return false;
        }
        return !facilitator.usernameExists(username);
    }

    public static boolean isPasswordOk() {
        return passwordOk;
    }

    public static boolean isUsernameOk() {
        return usernameOk;
    }

    public static boolean isAddressOk() {
        return addressOk;
    }

    public static boolean isEmailOk() {
        System.out.println(emailOk);
        return emailOk;
    }

    public static boolean isRepeatPwOk() {
        return repeatPwOk;
    }

    public static boolean isPhoneOk() {
        return phoneOk;
    }
}
