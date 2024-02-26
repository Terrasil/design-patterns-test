package src.creation_method.factories;

import src.creation_method.products.Clothes;
import src.creation_method.products.fabric.FabricPants;
import src.creation_method.products.leather.LeatherBoots;
import src.creation_method.products.leather.LeatherJacket;
import src.creation_method.products.leather.LeatherPants;

public class LeatherClothesFactory extends Factory {
  @Override
  public Clothes createClothes(String type) throws Exception {
    if (type.equals("jacket")) {
      return new LeatherJacket();
    } else if (type.equals("pants")) {
      return new LeatherPants();
    } else if (type.equals("boots")) {
      return new LeatherBoots();
    } else {
      throw new Exception("Wrong type");
    }
  }

}
