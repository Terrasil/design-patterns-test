package src.registration.creation_method;

import src.creation_method.products.Clothes;
import src.creation_method.products.leather.LeatherBoots;
import src.creation_method.products.leather.LeatherJacket;
import src.creation_method.products.leather.LeatherPants;

public class LeatherClothesRegistrationFactory extends RegistrationFactory {
  @Override
  public Clothes createClothes(String type) throws Exception {
    Class<? extends Clothes> clazz;
    switch (type) {
      case "jacket":
        clazz = registeredClothes.get("leatherjacket");
        if (clazz == null) {
          RegistrationFactory.registerComponent("leatherjacket", LeatherJacket.class);
          clazz = registeredClothes.get("leatherjacket");
        }
        break;
      case "pants":
        clazz = registeredClothes.get("leatherpants");
        if (clazz == null) {
          RegistrationFactory.registerComponent("leatherpants", LeatherPants.class);
          clazz = registeredClothes.get("leatherpants");
        }
        break;
      case "boots":
        clazz = registeredClothes.get("leatherboots");
        if (clazz == null) {
          RegistrationFactory.registerComponent("leatherboots", LeatherBoots.class);
          clazz = registeredClothes.get("leatherboots");
        }
        break;
      default:
        throw new Exception("No such component");
    }
    return clazz.getDeclaredConstructor().newInstance();
  }
}
