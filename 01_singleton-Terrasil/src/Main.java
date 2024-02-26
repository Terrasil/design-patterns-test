package src;

public class Main {
    public static void main(String[] args) {
        Singleton first = FirstSingleton.getInstance();
        Singleton second = SecondSingleton.getInstance();
        System.out.println(" [First]: " + first);
        System.out.println("[Second]: " + second);
    }

}