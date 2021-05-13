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
        if(!usernameExists(username, facilitator)){
            warnings.add("Username already in use.");
            usernameOk = false;
        } else if(username.isEmpty()) {
            warnings.add("Enter a username.");
            usernameOk = false;
        } else {
            passwordOk = true;
        }

        //check password
        if(password.length() < PASSWORD_MIN_LENGTH) {
            warnings.add("Password must be at least 8 characters long");
            passwordOk = false;
        } else {
            passwordOk = true;
        }

        if(!isPasswordValid(password)) {
            warnings.add("Password contain at least one uppercase letter and one number");
            passwordOk = false;
        } else {
            passwordOk = true;
        }

        //check email
        if(!email.contains("@")){
            warnings.add("Enter a valid email");
            emailOk = false;
        } else {
            emailOk = true;
        }

        if(!repeated.equals(password)) {
            warnings.add("Passwords must match");
            repeatPwOk = false;
        } else {
            repeatPwOk = true;
        }

        try{
            Integer.parseInt(phone);
            phoneOk = true;
        } catch (NumberFormatException e) {
            warnings.add("Enter a valid number");
            phoneOk = false;
        }


        if(warnings.isEmpty()){
            return null;
        } else {
            return warnings;
        }
    }

    private static boolean usernameExists(String username, GUIFacilitator facilitator) {
        //TODO: Implement check username function
        return !facilitator.usernameExists(username);
    }

    private static boolean isPasswordValid(String password) {
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
