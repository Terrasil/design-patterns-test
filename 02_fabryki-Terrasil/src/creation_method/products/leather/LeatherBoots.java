package src.creation_method.products.leather;

import src.creation_method.products.Clothes;

public class LeatherBoots extends Clothes {
    public LeatherBoots() {
        name = "Leather Boots";
        description = "Description of Leather Boots";
        price = 525.00;
        type = "boots";
    }

    public void sale() {
        System.out.println("Selling " + this.name + " for " + this.price);
    }
}
