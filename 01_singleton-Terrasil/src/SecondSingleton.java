package src;

public class SecondSingleton extends Singleton {
    private static Singleton uniqueInstance;

    private SecondSingleton() {
        System.out.println("Initializing SecondSingleton instance");
    }

    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    System.out.println("Invoke initializing SecondSingleton");
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}