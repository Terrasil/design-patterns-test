package Visitor;

import Observer.Subscriber.BuyerBankSubscriber;
import Visitor.Data.MarketItem;
import Visitor.Visitors.MarketVisitorImpl;
import Observer.Subscriber.BuyerSellerSubscriber;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public class Buyer {
  private double money;
  private BuyerBankSubscriber buyerBankSubscriber = new BuyerBankSubscriber();
  private BuyerSellerSubscriber buyerSellerSubscriber = new BuyerSellerSubscriber();
  private Seller seller;
  private Bank bank;
  public List<SimpleEntry<Integer, MarketItem>> cart = new ArrayList<>();
  //public double tendency;

  public Buyer() {
  }

  public Buyer(Bank bank, Seller seller, double money) {
    if (money < 0) {
      throw new IllegalArgumentException();
    }
    this.seller = seller;
    this.bank = bank;
    this.buyerBankSubscriber.subscribe(bank.getProvider(), this);
    this.buyerSellerSubscriber.subscribe(seller.getProvider(), this);
    this.money = money;
    //this.tendency = 0.5;
  }

  public double calculatePrice(MarketItem item) {
    var visitor = new MarketVisitorImpl();
    return item.accept(visitor);
  }

  public void analyseMarket(Type type, double price, int amount) {
    if (amount < 0 || price < 0) {
      throw new IllegalArgumentException();
    }
    for (MarketItem item : this.seller.getItems()) {
      double calculatedPrice = calculatePrice(item);
      if (item.getClass() == type && price >= calculatedPrice && this.money - calculatedPrice >= 0
          && item.getAmount() - amount >= 0) {
        buy(amount, item);
        return;
      }
    }
    throw new IllegalArgumentException();
  }

//  public void analyseMarketByTendency(Type type, double price, int amount, double maxTendency) {
//    if (amount < 0 || price < 0 || tendency < 0) {
//      throw new IllegalArgumentException();
//    }
//    for (MarketItem item : this.seller.getItems()) {
//      double calculatedPrice = calculatePrice(item);
//      if (item.getClass() == type && price >= calculatedPrice && money - calculatedPrice >= 0
//          && item.getAmount() - amount >= 0) {
//        calculateTendency(item);
//        if (this.tendency <= maxTendency) {
//          buy(amount, item);
//          return;
//        }
//      }
//    }
//    throw new IllegalArgumentException();
//  }
//
//  public void calculateTendency(MarketItem item) {
//    this.tendency = 0.5;
//    if (this.bank.getPrevInflation() == 0 && item.getPrevMargin() == 0
//        || this.bank.getPrevInflation() == this.bank.getInflation()) {
//      this.tendency += 0.5;
//    } else if (this.bank.getInflation() > this.bank.getPrevInflation()) {
//      this.tendency -= 0.3;
//    } else if (item.getMargin() > item.getPrevMargin() && item.getPrevMargin() != 0) {
//      this.tendency -= 0.5;
//    }
//  }
//
//  public String showTendency() {
//    return Double.toString(this.tendency);
//  }

  public String showCart() {
    String res = "";
    for (var item : this.cart) {
      res += item.getKey().toString() + ": " + item.getValue().getName() + "\n";
    }
    return res;
  }

  public String showBalance() {
    return Double.toString(this.money);
  }

  public void buy(int amount, MarketItem item) {
    if (item.getAmount() < amount) {
      throw new IllegalArgumentException("Not enough items in stock.");
    }

    this.cart.add(new SimpleEntry<Integer, MarketItem>(amount, item));
    this.money -= calculatePrice(item) * amount;

    item.setAmount(item.getAmount() - amount);
    item.setSold(item.getSold() + amount);

    this.seller.updateItem(item);
  }

}
