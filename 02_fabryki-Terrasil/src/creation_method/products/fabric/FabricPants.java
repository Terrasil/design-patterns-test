package src.creation_method.products.fabric;

import src.creation_method.products.Clothes;

public class FabricPants extends Clothes {
    public FabricPants() {
        name = "Fabric Pants";
        description = "Description of Fabric Pants";
        price = 225.00;
        type = "pants";
    }

    public void sale() {
        System.out.println("Selling " + this.name + " for " + this.price);
    }
}
