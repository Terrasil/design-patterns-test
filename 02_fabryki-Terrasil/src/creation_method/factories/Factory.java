package src.creation_method.factories;

import src.creation_method.products.Clothes;

public abstract class Factory {

  public Clothes makeClothes(String genre) {
    System.out.println("Making clothes...");
    try {
      Clothes clothes = createClothes(genre);
      clothes.make();
      return clothes;
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
      return null;
    }
  }

  public abstract Clothes createClothes(String type) throws Exception;
}
