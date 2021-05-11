package Model;

/**
 * The category-object.
 */
public class Category {

    private String name;

    public Category(String name){
        this.name = name;
    }

    public String toString() {
        return String.format(" %s ", name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
