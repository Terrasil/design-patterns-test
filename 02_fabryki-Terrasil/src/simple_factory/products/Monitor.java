package src.simple_factory.products;

public class Monitor implements Peripherals {
    @Override
    public void make() {
        System.out.println("Monitor is being made");
    }

    @Override
    public void clean() {
        System.out.println("Monitor is being cleaned");
    }

    @Override
    public void repair() {
        System.out.println("Monitor is being repaired");
    }

    @Override
    public void sale() {
        System.out.println("Monitor is being sold");
    }

    @Override
    public void use() {
        System.out.println("Monitor is being used");
    }
}
