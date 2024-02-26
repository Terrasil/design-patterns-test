package simple_factory;

import org.junit.jupiter.api.Test;
import src.simple_factory.factories.PeripheralsFactory;
import src.simple_factory.factories.SimpleFactory;
import src.simple_factory.products.Peripherals;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleFactoryTest {
  @Test
  public void shouldPeripheralsByFactoryWithSingletonFactoryUsedInside() {
    PeripheralsFactory factory = new PeripheralsFactory(SimpleFactory.getInstance());

    List<Peripherals> components = new ArrayList<>();

    try {
      components.add(factory.createPeripherals("keyboard"));
      components.add(factory.createPeripherals("mouse"));
      components.add(factory.createPeripherals("monitor"));
      components.add(factory.createPeripherals("pad"));
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }

    for (Peripherals component : components) {
      assertTrue(component instanceof Peripherals);
    }
  }
}
