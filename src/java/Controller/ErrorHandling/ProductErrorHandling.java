package Controller.ErrorHandling;

public class ProductErrorHandling {

    public static boolean errorHandling(String name, String stock, String price, String cost, String category, String shelf, String supplier){
        if(!isNameValid(name)) {
            return false;
        }

        if(!isStockValid(stock)){
            return false;
        }

        if(!isPriceValid(price)) {
            return false;
        }

        if(!isCostValid(cost)) {
            return false;
        }

        if(!isCategoryValid(category)) {
            return false;
        }

        if(!isShelfValid(shelf)) {
            return false;
        }

        if(!isSupplierValid(supplier)) {
            return false;
        }

        return true;
    }

    public static boolean updateErrorHandling(int id, String name, String stock, String price, String cost, String category, String shelf) {
        if(!isIDValid(id)) {
            return false;
        }

        if(!isNameValid(name)) {
            return false;
        }

        if(!isStockValid(stock)){
            return false;
        }

        if(!isPriceValid(price)) {
            return false;
        }

        if(!isCostValid(cost)) {
            return false;
        }

        if(!isCategoryValid(category)) {
            return false;
        }

        if(!isShelfValid(shelf)) {
            return false;
        }

        return true;
    }

    public static boolean isSupplierValid(String supplier) {
        return supplier != null && !supplier.equals("");
    }

    public static boolean isShelfValid(String shelf) {
        if(shelf == null || shelf.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isCategoryValid(String category) {
        return category != null && !category.equals("");
    }

    public static boolean isStockValid(String stock) {
        try{
            Integer.parseInt(stock);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isPriceValid(String price) {
        try{
            Double.parseDouble(price);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isCostValid(String cost) {
        try{
            Double.parseDouble(cost);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isNameValid(String text) {
        if(text == null || text.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isIDValid(int id) {
        if(id > 0) {
            return true;
        } else return false;
    }
}
