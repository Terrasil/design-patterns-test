package src.creation_method.products.fabric;

import src.creation_method.products.Clothes;

public class FabricBoots extends Clothes {
    public FabricBoots() {
        name = "Fabric Boots";
        description = "Description of Fabric Boots";
        price = 625.00;
        type = "boots";
    }

    public void sale() {
        System.out.println("Selling " + this.name + " for " + this.price);
    }
}
