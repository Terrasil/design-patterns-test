package src;
import java.io.Serializable;

public class SingletonPerThread implements Serializable {
    private static ThreadLocal<SingletonPerThread> uniqueInstanceThreadLocal =
            ThreadLocal.withInitial(() -> new SingletonPerThread());

    protected SingletonPerThread() {
        System.out.println("Initializing SingletonPerThread instance");
    }

    public static SingletonPerThread getInstance() {
        return uniqueInstanceThreadLocal.get();
    }

    // 3. Problem serializacji i deserializacji obiektów klasy Singleton
    protected Object readResolve() {
        return getInstance();
    }
}