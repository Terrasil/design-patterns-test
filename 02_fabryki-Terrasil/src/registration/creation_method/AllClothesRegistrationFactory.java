package src.registration.creation_method;

import src.creation_method.products.Clothes;

public class AllClothesRegistrationFactory {
  public Clothes makeClothes(String type, String product) {
    RegistrationFactory registrationFactory = null;
    if (type.equals("leather")) {
      registrationFactory = new LeatherClothesRegistrationFactory();
    } else if (type.equals("fabric")) {
      registrationFactory = new FabricClothesRegistrationFactory();
    }
    try {
      Clothes clothes = registrationFactory.makeClothes(product);
      return clothes;
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
      return null;
    }
  }
}
