package src.creation_method.products;

public abstract class Clothes {
    protected String name;
    protected String description;
    protected Double price;
    protected String type;

    public void make() {
        System.out.println("Making " + this.getClass().getSimpleName());
    }
    public void buy() {
        System.out.println("Buying " + this.getClass().getSimpleName());
    }
    public void clean() {
        System.out.println("Cleaning " + this.getClass().getSimpleName());
    }

    public String toString() {
        return "Name: " + this.name +", Description: " + this.description + ", Price: " + this.price
                + ", Type: " + this.type;
    }
}
