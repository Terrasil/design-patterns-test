package src.creation_method.factories;

import src.creation_method.products.Clothes;
import src.creation_method.products.fabric.FabricBoots;
import src.creation_method.products.fabric.FabricJacket;
import src.creation_method.products.fabric.FabricPants;
import src.creation_method.products.leather.LeatherJacket;
import src.creation_method.products.leather.LeatherPants;

public class FabricClothesFactory extends Factory {
  @Override
  public Clothes createClothes(String type) throws Exception {
    if (type.equals("jacket")) {
      return new FabricJacket();
    } else if (type.equals("pants")) {
      return new FabricPants();
    } else if (type.equals("boots")) {
      return new FabricBoots();
    } else {
      throw new Exception("Wrong type");
    }
  }

}
