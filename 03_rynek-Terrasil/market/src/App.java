import Visitor.Buyer;
import Visitor.Bank;
import Visitor.Data.Rollers;
import Visitor.Seller;
import Visitor.Data.Bike;
import Visitor.Data.Scooter;

public class App {
    public static void main(String[] args) {
        Bank bank = new Bank(20, 100000);

        Seller seller = new Seller(bank);

        Buyer buyer = new Buyer(bank, seller, 20000);

        Integer roundManipulation = 5;
        Integer manipulatedInflationValue = 10;

        Integer rounds = 10;
        for (int i = 1; i <= rounds; i++) {
            System.out.println("--- Runda " + (i) + " ---");

            try {
                buyer.analyseMarket(Bike.class, 2000, 2);
                buyer.analyseMarket(Rollers.class, 1000, 4);
                buyer.analyseMarket(Scooter.class, 1500, 1);
            } catch (Exception e) {
                System.out.println("Nie udało się dokonać zakupu: " + e.getMessage());
            }

            bank.updateInflation();

            if(i == roundManipulation) {
                bank.setInflation(manipulatedInflationValue); // Manipulacja inflacją
                System.out.println("--- /!\\ ---");
                System.out.println("> Manipulacja inflacją: " + manipulatedInflationValue);
                System.out.println("--- /!\\ ---");
            }

            System.out.println("Stan konta banku centralnego: " + bank.getIncome());
            System.out.println("Inflacja: " + bank.getInflation());
            System.out.println("Stan konta kupującego: " + buyer.showBalance());
            System.out.println("Koszyk kupującego: ");
            System.out.println(buyer.showCart());
            System.out.println("Asortyment sprzedawcy: ");
            System.out.println(seller.showItems());
        }
    }
}