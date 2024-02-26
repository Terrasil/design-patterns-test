package src.simple_factory.factories;

import src.simple_factory.products.Peripherals;

public class PeripheralsFactory {
    SimpleFactory simpleFactory;

    public PeripheralsFactory(SimpleFactory simpleFactory) {
        this.simpleFactory = simpleFactory;
    }

    public Peripherals createPeripherals(String type) throws Exception {
        return simpleFactory.createPeripherals(type);
    }
}
