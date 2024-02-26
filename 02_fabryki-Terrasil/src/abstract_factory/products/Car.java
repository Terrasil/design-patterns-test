package src.abstract_factory.products;

public class Car implements Vehicle {
    public Car() {
        System.out.println("Car is created");
    }

    @Override
    public Car make() {
        System.out.println("Car is made");
        return this;
    }

    @Override
    public void repair() {
        System.out.println("Car is repaired");
    }

    @Override
    public void sale() {
        System.out.println("Car is sold");
    }
}
