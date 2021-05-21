package Controller.ErrorHandling;

public class EditAccountSettingHandling {

    private static final int PASSWORD_MIN_LENGTH = 8;

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

    public static boolean isAreaCodeValid(String areaCode) {
        if(areaCode == null) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isPhoneValid(String phone) {
        try{
            if(Integer.parseInt(phone) < 10000000) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isRepeatValid(String password, String repeated) {
        return (!repeated.equals(password));
    }

}
