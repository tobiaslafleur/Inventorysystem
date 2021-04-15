package Tests;

import Controller.Controller;
import Controller.DBController;
import Controller.Database.DBProduct;

import java.math.BigDecimal;

public class AddProductTest {
    public static void main(String[] args) {
        DBProduct dbProduct = new DBProduct(new DBController(new Controller()));
        if(dbProduct.addProduct("name", 5, new BigDecimal("price"), 1, "shelfPosition", 2, new BigDecimal("cost"), 1)) {
            System.out.println("Add product test Successful");
        }
    }
}
