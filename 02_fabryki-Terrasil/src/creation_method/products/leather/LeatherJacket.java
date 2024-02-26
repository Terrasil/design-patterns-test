package src.creation_method.products.leather;

import src.creation_method.products.Clothes;

public class LeatherJacket extends Clothes {
    public LeatherJacket() {
        name = "Leather Jacket";
        description = "Description of Leather Jacket";
        price = 550.00;
        type = "jacket";
    }

    public void sale() {
        System.out.println("Selling " + this.name + " for " + this.price);
    }
}
