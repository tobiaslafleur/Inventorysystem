package Tests;

import Controller.Controller;
import Controller.DBController;
import Controller.Database.DBProduct;

import java.math.BigDecimal;

public class AddProductTest {
    public static void main(String[] args) {
        DBProduct dbProduct = new DBProduct(new DBController(new Controller()));
        if(dbProduct.addProduct("PS5", 5, new BigDecimal("5999.99"), 1, "somewhere", 2, new BigDecimal("2999.99"), 2)) {
            System.out.println("Add product test Successful");
        }
    }
}
