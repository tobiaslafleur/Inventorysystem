package Controller.ErrorHandling;

public class ProductErrorHandling {

    public static boolean errorHandling(String stock, String price, String cost, String category, String shelf, String supplier){

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

    public static boolean isSupplierValid(String supplier) {
        return supplier != null && !supplier.equals("");
    }

    public static boolean isShelfValid(String shelf) {
        return shelf != null && !shelf.equals("");
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
        return text != null && !text.equals("");
    }
}
