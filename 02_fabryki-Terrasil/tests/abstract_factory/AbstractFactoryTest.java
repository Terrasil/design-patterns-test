package abstract_factory;

import org.junit.jupiter.api.Test;
import src.Product;
import src.abstract_factory.factories.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class AbstractFactoryTest {

  @Test
  public void shouldCreateProducts() {
    List<VehicleFactory> factories = new ArrayList<>();

    factories.add(new CarFactory());
    factories.add(new HelicopterFactory());
    factories.add(new MotorFactory());
    factories.add(new PlaneFactory());

    List<Product> components = new ArrayList<>();

    for(VehicleFactory factory : factories) {
      components.add(factory.makeVehicle());
    }

    for(Product component : components) {
      assertTrue(component instanceof Product);
    }
  }
}
