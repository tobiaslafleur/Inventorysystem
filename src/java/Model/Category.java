package Model;

/**
 * The category-object.
 */
public class Category {
    private int ID;
    private String name;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Category(String name, int ID){
        this.name = name;
        this.ID = ID;
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
