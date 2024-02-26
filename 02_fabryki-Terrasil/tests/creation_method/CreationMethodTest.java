package creation_method;

import org.junit.jupiter.api.Test;
import src.creation_method.factories.AllClothesFactory;
import src.creation_method.factories.FabricClothesFactory;
import src.creation_method.factories.Factory;
import src.creation_method.factories.LeatherClothesFactory;
import src.creation_method.products.Clothes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreationMethodTest {

  @Test
  public void shouldCreateClothesByAllClothesFactory() {
    AllClothesFactory factory = new AllClothesFactory();

    List<Clothes> components = new ArrayList<>();

    components.add(factory.makeClothes("leather", "jacket"));
    components.add(factory.makeClothes("leather", "pants"));
    components.add(factory.makeClothes("leather", "boots"));
    components.add(factory.makeClothes("fabric", "jacket"));
    components.add(factory.makeClothes("fabric", "pants"));
    components.add(factory.makeClothes("fabric", "boots"));

    for (Clothes component : components) {
      assertTrue(component instanceof Clothes);
    }
  }

  @Test
  public void shouldCreateClothesBySpecifyFactory() {
    List<Factory> factories = new ArrayList<>();

    factories.add(new FabricClothesFactory());
    factories.add(new LeatherClothesFactory());

    List<Clothes> components = new ArrayList<>();

    for (Factory factory : factories) {
      try {
        components.add(factory.makeClothes("jacket"));
        components.add(factory.makeClothes("pants"));
        components.add(factory.makeClothes("boots"));
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }
    }

    for (Clothes component : components) {
      assertTrue(component instanceof Clothes);
    }
  }
}
