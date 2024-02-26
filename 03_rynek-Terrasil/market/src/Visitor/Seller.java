package Visitor;

import Observer.Subscriber.SellerBankSubscriber;
import Observer.Publisher.Seller.SellerDataProvider;
import Visitor.Data.MarketItem;
import Visitor.Data.Bike;
import Visitor.Data.Rollers;
import Visitor.Data.Scooter;

import java.util.ArrayList;
import java.util.List;

public class Seller {
  private List<MarketItem> items = new ArrayList<>();
  private Bank bank;
  private SellerBankSubscriber bankSubscriber = new SellerBankSubscriber();
  private SellerDataProvider provider;

  public Seller() {
    provider = new SellerDataProvider();
    addItems();
    provider.setMeasurements(items);
  }

  public Seller(Bank bank) {
    provider = new SellerDataProvider();
    addItems();
    provider.setMeasurements(items);
    this.bank = bank;
    bankSubscriber.subscribe(bank.getProvider(), this);
    bank.setSeller(this);
  }

  public void addItems() {
    items.add(new Bike("Rower", 1500.0, 30, 5));
    items.add(new Rollers("Rolki", 600.0, 15, 25));
    items.add(new Scooter("Hulajnoga", 1200.0, 25, 10));
  }

  public void changeMargin(Class<? extends MarketItem> type, int margin) {
    for (MarketItem item : items) {
      if (item.getClass() == type) {
        item.setPrevMargin(item.getMargin());
        item.setMargin(margin);
      }
    }
  }

  public void updateCostByInflation() {
    for (MarketItem item : items) {
      item.setCost(item.getCost() + (item.getCost() * bank.getInflation() / 100));
    }
    provider.setMeasurements(items);
  }

  public void sell(int amount, Class<? extends MarketItem> type) {
    for (MarketItem item : items) {
      if (item.getClass() == type) {
        if (item.getAmount() >= amount) {
          item.setAmount(item.getAmount() - amount);
          item.setSold(item.getSold() + amount);
          provider.setMeasurements(items);
        } else {
          throw new IllegalArgumentException();
        }
      }
    }
  }

  public String showItems() {
    StringBuilder sb = new StringBuilder();
    for (MarketItem item : items) {
      sb.append(item.toString());
      sb.append("\n");
    }
    return sb.toString();
  }

  public List<MarketItem> getItems() {
    return items;
  }

  public SellerDataProvider getProvider() {
    return provider;
  }

  public void updateItem(MarketItem item) {
    for (int i = 0; i < items.size(); i++) {
      if (items.get(i).getName().equals(item.getName())) {
        items.set(i, item);
        break;
      }
    }

    provider.setMeasurements(items);
  }

}
