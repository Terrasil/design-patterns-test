package src.creation_method.products.leather;

import src.creation_method.products.Clothes;

public class LeatherPants extends Clothes {
    public LeatherPants() {
        name = "Leather Pants";
        description = "Description of Leather Pants";
        price = 425.00;
        type = "pants";
    }

    public void sale() {
        System.out.println("Selling " + this.name + " for " + this.price);
    }
}
