package Tests;

import Controller.Controller;
import Controller.DBController;
import Controller.Database.DBCategory;
import Model.Category;

import javax.security.auth.callback.Callback;

public class AddCategoryTest {
    public static void main(String[] args) {
        DBCategory dbCategory = new DBCategory(new DBController(new Controller()));
        Category category = new Category("Cups");
        if(dbCategory.createCategory(category)) {
            System.out.println("Test Successful");
        }
    }
}
