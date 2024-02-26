import kingdom.Kingdom;

public class Main {
    private static Kingdom kingdom = Kingdom.getInstance();

    public static void main(String[] args) throws InterruptedException {
        kingdom.initialize();
        while(kingdom.stillExist()){
            kingdom.describeKingdom();
            kingdom.simulateDay();
        }
    }
}