package Controller.ErrorHandling;

import View.GUIFacilitator;

import java.util.ArrayList;

public class RegistrationPageHandling {

    private static final int PASSWORD_MIN_LENGTH = 8;
    private static ArrayList<String> warnings = new ArrayList<>();


    public static boolean errorHandling(String username, String email, String phone, /*String areacode,*/ String password, String repeated, GUIFacilitator facilitator) {
        //username
        if(!isUsernameValid(username, facilitator)){
            return false;
        } else if(username.isEmpty()) {
            return false;
        }

        //check password
        if(!isPasswordValid(password)) {
            return false;
        }

        //check email
        if(!isEmailValid(email)){
            return false;
        }

        if(!isRepeatValid(password, repeated)) {
            return false;
        }

        if(!isPhoneValid(phone)) {
            return false;
        }

//        if(!isAreaCodeValid(areacode)) {
//            return false;
//        }
        return true;
    }

    public static boolean isAreaCodeValid(String areacode) {
        if(areacode == null) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isPhoneValid(String phone) {
        try{
            if(Integer.parseInt(phone) > 1000/* > 100000*/) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        return false;
    }

    public static boolean isRepeatValid(String password, String repeated) {
        return repeated.equals(password);
    }

    public static boolean isEmailValid(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isUsernameValid(String username, GUIFacilitator facilitator){
        if(!usernameExists(username, facilitator)){
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
}
