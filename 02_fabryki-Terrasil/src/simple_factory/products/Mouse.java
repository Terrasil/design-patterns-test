package src.simple_factory.products;

public class Mouse implements Peripherals {
    @Override
    public void make() {
        System.out.println("Mouse is being made");
    }

    @Override
    public void clean() {
        System.out.println("Mouse is being cleaned");
    }

    @Override
    public void repair() {
        System.out.println("Mouse is being repaired");
    }

    @Override
    public void sale() {
        System.out.println("Mouse is being sold");
    }

    @Override
    public void use() {
        System.out.println("Mouse is being used");
    }
}
