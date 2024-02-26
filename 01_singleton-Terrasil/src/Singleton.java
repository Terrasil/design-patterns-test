package src;
import java.io.Serializable;

public class Singleton implements Serializable {
    private static Singleton uniqueInstance;

    protected Singleton() {
        System.out.println("Initializing Singleton instance");
    }

    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    System.out.println("Invoke initializing Singleton");
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }

    protected Object readResolve() {
        return getInstance();
    }
}