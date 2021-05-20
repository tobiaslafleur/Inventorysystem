package Controller.ErrorHandling;

import View.GUIFacilitator;

import java.util.ArrayList;

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

    private static boolean isSupplierValid(String supplier) {
        if(supplier == null) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean isShelfValid(String shelf) {
        if(shelf == null) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean isCategoryValid(String category) {
        if(category == null) {
            return false;
        } else {
            return true;
        }
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
}
