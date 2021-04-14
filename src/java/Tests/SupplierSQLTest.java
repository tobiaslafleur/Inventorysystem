package Tests;
import Controller.*;
import Controller.Database.DBProduct;

public class SupplierSQLTest {

    public static void testAddSupplier() {
        DBProduct  dbProduct = new DBProduct(new DBController(new Controller()));
        if(dbProduct.addSupplier("OJ", "555-55-55", "Cali Prob", "Pang pang")) {
            System.out.println("hell yeah");
        }
    }

    public static void main(String[] args) {
        testAddSupplier();
    }

}
