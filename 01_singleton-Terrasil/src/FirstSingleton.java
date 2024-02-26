package src;

public class FirstSingleton extends Singleton {
    private FirstSingleton() {
        super();
        System.out.println("FirstSingleton constructor");
    }
}