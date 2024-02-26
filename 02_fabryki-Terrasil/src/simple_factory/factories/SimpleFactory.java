package src.simple_factory.factories;

import src.BaseFactory;
import src.simple_factory.products.*;

public class SimpleFactory implements BaseFactory {
  private static SimpleFactory instance = null;

  public static synchronized SimpleFactory getInstance() {
    if (instance == null) {
      synchronized (SimpleFactory.class) {
        if (instance == null) {
          instance = new SimpleFactory();
        }
      }
    }
    return instance;
  }
  public Peripherals createPeripherals(String type) throws Exception {
    switch (type) {
      case "keyboard":
        return new Keyboard();
      case "monitor":
        return new Monitor();
      case "mouse":
        return new Mouse();
      case "pad":
        return new Pad();
      default:
        throw new Exception("Wrong type");
    }
  }

}
