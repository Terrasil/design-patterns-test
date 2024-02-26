package src.registration.creation_method;

import src.creation_method.products.Clothes;

import java.util.HashMap;
import java.util.Map;

public abstract class RegistrationFactory {
  public Clothes makeClothes(String type) {
    System.out.println("Making clothes...");
    try {
      Clothes clothes = createClothes(type);
      clothes.make();
      return clothes;
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
      return null;
    }
  }

  protected static final Map<String, Class<? extends Clothes>> registeredClothes = new HashMap<>();

  public static void registerComponent(String name, Class<? extends Clothes> clazz) {
    registeredClothes.put(name, clazz);
  }

  public static void registerClothes(String name, String className) throws ClassNotFoundException {
    Class<? extends Clothes> clazz = Class.forName(className).asSubclass(Clothes.class);
    registeredClothes.put(name, clazz);
  }

  public static void registerClothes(String name, Clothes clothes) {
    registeredClothes.put(name, clothes.getClass());
  }

  public abstract Clothes createClothes(String type) throws Exception;

}
