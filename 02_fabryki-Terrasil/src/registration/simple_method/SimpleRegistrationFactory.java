package src.registration.simple_method;

import src.BaseFactory;
import src.simple_factory.products.*;

import java.util.HashMap;
import java.util.Map;

public class SimpleRegistrationFactory implements BaseFactory {
  private static SimpleRegistrationFactory instance = null;

  public static synchronized SimpleRegistrationFactory getInstance() {
    if (instance == null) {
      synchronized (SimpleRegistrationFactory.class) {
        if (instance == null) {
          instance = new SimpleRegistrationFactory();
        }
      }
    }
    return instance;
  }

  private final Map<String, Class<? extends Peripherals>> registeredPeripherals = new HashMap<>();

  public void registerPeripherals(String name, Class<? extends Peripherals> clazz) {
    registeredPeripherals.put(name, clazz);
  }

  public void registerPeripherals(String name, String className) throws ClassNotFoundException {
    Class<? extends Peripherals> clazz = Class.forName(className).asSubclass(Peripherals.class);
    registeredPeripherals.put(name, clazz);
  }

  public void registerPeripherals(String name, Peripherals product) {
    registeredPeripherals.put(name, product.getClass());
  }

  public Peripherals createPeripherals(String name) throws Exception {
    Class<? extends Peripherals> clazz = registeredPeripherals.get(name);
    if (clazz == null) {
      throw new IllegalArgumentException("No registered product found with name " + name);
    }
    return clazz.getDeclaredConstructor().newInstance();
  }
}
