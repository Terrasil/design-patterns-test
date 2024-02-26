package src;
import java.io.Serializable;

public class SlowSingleton implements Serializable {
    private static SlowSingleton uniqueInstance;

    protected SlowSingleton() {
        System.out.println("Initializing Singleton instance");
    }

    public static synchronized SlowSingleton getInstance() {
        if (uniqueInstance == null) {
            System.out.println("Invoke initializing Singleton");
            uniqueInstance = new SlowSingleton();
        }
        return uniqueInstance;
    }

    protected Object readResolve() {
        return getInstance();
    }
}