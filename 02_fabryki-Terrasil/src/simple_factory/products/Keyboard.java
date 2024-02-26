package src.simple_factory.products;

public class Keyboard implements Peripherals {
    @Override
    public void make() {
        System.out.println("Keyboard is being made");
    }

    @Override
    public void clean() {
        System.out.println("Keyboard is being cleaned");
    }

    @Override
    public void repair() {
        System.out.println("Keyboard is being repaired");
    }

    @Override
    public void sale() {
        System.out.println("Keyboard is being sold");
    }

    @Override
    public void use() {
        System.out.println("Keyboard is being used");
    }
}
