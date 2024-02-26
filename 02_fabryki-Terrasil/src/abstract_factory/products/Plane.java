package src.abstract_factory.products;

public class Plane implements Vehicle {
    public Plane() {
        System.out.println("Plane is created");
    }

    @Override
    public Plane make() {
        System.out.println("Plane is made");
        return this;
    }

    @Override
    public void repair() {
        System.out.println("Plane is repaired");
    }

    @Override
    public void sale() {
        System.out.println("Plane is sold");
    }
}
