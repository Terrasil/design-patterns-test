package src.creation_method.products.fabric;

import src.creation_method.products.Clothes;

public class FabricJacket extends Clothes {
    public FabricJacket() {
        name = "Fabric Jacket";
        description = "Description of Fabric Jacket";
        price = 350.00;
        type = "jacket";
    }

    public void sale() {
        System.out.println("Selling " + this.name + " for " + this.price);
    }
}
