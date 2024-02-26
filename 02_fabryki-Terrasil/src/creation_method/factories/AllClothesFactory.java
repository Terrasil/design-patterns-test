package src.creation_method.factories;

import src.creation_method.products.Clothes;

public class AllClothesFactory {
  public Clothes makeClothes(String type, String product) {
    Factory factory = null;
    if (type.equals("leather")) {
      factory = new LeatherClothesFactory();
    } else if (type.equals("fabric")) {
      factory = new FabricClothesFactory();
    }
    try {
      Clothes clothes = factory.makeClothes(product);
      return clothes;
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
      return null;
    }
  }
}
