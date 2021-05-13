package Controller.ErrorHandling;

import View.GUIFacilitator;

import java.util.ArrayList;

public class ProductErrorHandling {

    private static boolean stockOk = true;
    private static boolean priceOk = true;
    private static boolean costOk = true;

    private static ArrayList<String> warnings = new ArrayList<>();

    public static ArrayList<String> errorHandling(String stock, String price, String cost){

        if(!warnings.isEmpty()){
            warnings.clear();
        }

        try {
            Integer.parseInt(stock);
            stockOk = true;
        } catch (NumberFormatException e) {
            warnings.add("Only enter numbers");
            stockOk = false;
        }

        try {
            Double.parseDouble(price);
            priceOk = true;
        } catch(NumberFormatException e) {
            warnings.add("Enter numbers for the price only. If decimals are needed, use a . to separate.");
            priceOk = false;
        }

        try {
            Double.parseDouble(cost);
            costOk = true;
        } catch(NumberFormatException e) {
            warnings.add("Enter numbers the for cost only. If decimals are needed, use a . to separate.");
            costOk = false;
        }

        if(warnings.isEmpty()){
            return null;
        } else {
            return warnings;
        }
    }

    public static boolean isStockOk() {
        return stockOk;
    }

    public static boolean isPriceOk() {
        return priceOk;
    }

    public static boolean isCostOk() {
        return costOk;
    }
}
