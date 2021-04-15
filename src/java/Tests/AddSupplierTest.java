package Tests;
import Controller.*;
import Controller.Database.DBProduct;
import Controller.Database.DBSupplier;

public class AddSupplierTest {

    public static void testAddSupplier() {
        DBSupplier dbSupplier = new DBSupplier(new DBController(new Controller()));
        if(dbSupplier.addSupplier("name", "phone", "address", "email")) {
            System.out.println("Supplier test successful");
        }
    }

    public static void main(String[] args) {
        testAddSupplier();
    }

}
