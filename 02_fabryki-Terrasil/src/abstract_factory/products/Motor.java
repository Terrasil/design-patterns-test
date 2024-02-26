package src.abstract_factory.products;

public class Motor implements Vehicle {
    public Motor() {
        System.out.println("Motor is created");
    }

    @Override
    public Motor make() {
        System.out.println("Motor is made");
        return this;
    }

    @Override
    public void repair() {
        System.out.println("Motor is repaired");
    }

    @Override
    public void sale() {
        System.out.println("Motor is sold");
    }
}
