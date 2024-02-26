package src.abstract_factory.products;

public class Helicopter implements Vehicle {
    public Helicopter() {
        System.out.println("Helicopter is created");
    }

    @Override
    public Helicopter make() {
        System.out.println("Helicopter is made");
        return this;
    }

    @Override
    public void repair() {
        System.out.println("Helicopter is repaired");
    }

    @Override
    public void sale() {
        System.out.println("Helicopter is sold");
    }
}
