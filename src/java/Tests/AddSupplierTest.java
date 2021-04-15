package Tests;
import Controller.*;
import Controller.Database.DBProduct;
import Controller.Database.DBSupplier;
import Model.Supplier;

public class AddSupplierTest {

    public static void testAddSupplier() {
        DBSupplier dbSupplier = new DBSupplier(new DBController(new Controller()));
        Supplier supplier = new Supplier("name", "phone", "address", "email");
        if(dbSupplier.addSupplier(supplier)) {
            System.out.println("Supplier test successful");
        }
    }

    public static void main(String[] args) {
        testAddSupplier();
    }

}
