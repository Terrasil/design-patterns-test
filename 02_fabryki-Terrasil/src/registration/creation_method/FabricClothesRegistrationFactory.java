package src.registration.creation_method;

import src.creation_method.products.Clothes;
import src.creation_method.products.fabric.FabricBoots;
import src.creation_method.products.fabric.FabricJacket;
import src.creation_method.products.fabric.FabricPants;
import src.creation_method.products.leather.LeatherBoots;
import src.creation_method.products.leather.LeatherJacket;
import src.creation_method.products.leather.LeatherPants;

public class FabricClothesRegistrationFactory extends RegistrationFactory {
  @Override
  public Clothes createClothes(String type) throws Exception {
    Class<? extends Clothes> clazz;
    switch (type) {
      case "jacket":
        clazz = registeredClothes.get("fabricjacket");
        if (clazz == null) {
          RegistrationFactory.registerComponent("fabricjacket", FabricJacket.class);
          clazz = registeredClothes.get("fabricjacket");
        }
        break;
      case "pants":
        clazz = registeredClothes.get("fabricpants");
        if (clazz == null) {
          RegistrationFactory.registerComponent("fabricpants", FabricPants.class);
          clazz = registeredClothes.get("fabricpants");
        }
        break;
      case "boots":
        clazz = registeredClothes.get("fabricboots");
        if (clazz == null) {
          RegistrationFactory.registerComponent("fabricboots", FabricBoots.class);
          clazz = registeredClothes.get("fabricboots");
        }
        break;
      default:
        throw new Exception("No such component");
    }
    return clazz.getDeclaredConstructor().newInstance();
  }

}
