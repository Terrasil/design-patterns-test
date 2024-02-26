package src.simple_factory.products;

public class Pad implements Peripherals {
    @Override
    public void make() {
        System.out.println("Pad is being made");
    }

    @Override
    public void clean() {
        System.out.println("Pad is being cleaned");
    }

    @Override
    public void repair() {
        System.out.println("Pad is being repaired");
    }

    @Override
    public void sale() {
        System.out.println("Pad is being sold");
    }

    @Override
    public void use() {
        System.out.println("Pad is being used");
    }
}
