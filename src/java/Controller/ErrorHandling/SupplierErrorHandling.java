package Controller.ErrorHandling;

public class SupplierErrorHandling {

    public static boolean errorHandling(String name, String email, String phone, String street, String city, String country) {
        //username
        if(!isNameValid(name)){
            return false;
        } else if(name.isEmpty()) {
            return false;
        }

        //check password
        if(!isPhoneValid(phone)) {
            return false;
        }

        //check email
        if(!isEmailValid(email)){
            return false;
        }

        if(!isStreetValid(street)) {
            return false;
        }

        if(!isCityValid(city)) {
            return false;
        }

        if(!isCountryValid(country)) {
            return false;
        }
        return true;
    }

    public static boolean updateErrorHandling(String name, String phone, String street, String city, String country, String email, int id) {
        if(!isIDValid(id)) {
            return false;
        }

        if(!isNameValid(name)){
            return false;
        } else if(name.isEmpty()) {
            return false;
        }

        if(!isPhoneValid(phone)) {
            return false;
        }


        if(!isEmailValid(email)){
            return false;
        }

        if(!isStreetValid(street)) {
            return false;
        }

        if(!isCityValid(city)) {
            return false;
        }

        if(!isCountryValid(country)) {
            return false;
        }
        return true;
    }

    public static boolean isNameValid(String name) {
        if(name == null || name.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isPhoneValid(String phone) {
        try{
            if(Integer.parseInt(phone) > 1000) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        return false;
    }
    public static boolean isStreetValid(String street) {
        if(street == null || street.equals("")) {
            return false;
        } else {
            return true;
        }
    }
    public static boolean isCityValid(String city) {
        if(city == null || city.equals("")) {
            return false;
        } else {
            return true;
        }
    }
    public static boolean isCountryValid(String country) {
        return country != null && !country.equals("");
    }

    public static boolean isEmailValid(String email) {
        if(email == null || email.equals("")) {
            return false;
        }
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isIDValid(int id) {
        if(id > 0) {
            return true;
        } else return false;
    }
}
