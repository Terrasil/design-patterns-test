package Visitor;

import Observer.Publisher.Bank.BankDataProvider;
import Observer.Subscriber.BankSellerSubscriber;
import Visitor.Data.MarketItem;
import Visitor.Visitors.MarketVisitorImpl;

public class Bank {
  private int inflation;
  private double income;
  private int prevInflation;
  private Seller seller;
  private BankSellerSubscriber sellerSubscriber = new BankSellerSubscriber();
  private BankDataProvider provider;

  public Bank() {
  }

  public Bank(int inflation, double income, Seller seller) {
    if (inflation < 0 || income < 0) {
      throw new IllegalArgumentException();
    }
    this.seller = seller;
    sellerSubscriber.subscribe(seller.getProvider(), this);
    this.inflation = inflation;
    this.prevInflation = 0;
    this.income = income;
    provider = new BankDataProvider();
    provider.setMeasurements(inflation, 0, 0);
  }

  public Bank(int inflation, double income) {
    if (inflation < 0 || income < 0) {
      throw new IllegalArgumentException();
    }
    this.inflation = inflation;
    this.prevInflation = 0;
    this.income = income;
    provider = new BankDataProvider();
    provider.setMeasurements(inflation, 0, 0);
  }

  public double calculatePrice(MarketItem item) {
    var visitor = new MarketVisitorImpl();
    return item.accept(visitor);
  }

  public void updateInflation() {
    prevInflation = inflation;

    double turnover = 1;

    for (MarketItem item : this.seller.getItems()) {
      turnover += item.getSold() * calculatePrice(item);
    }

    if (Math.abs(turnover) < 1e-9) {
      throw new IllegalArgumentException("Turnover is too low");
    }

    turnover = turnover - (turnover * inflation / 100);
    this.income += turnover * inflation / 100;

    double incomeDiff = turnover * inflation / 100;

    inflationCalculate(incomeDiff, turnover, 200, 25);

    provider.setMeasurements(this.inflation, this.prevInflation, this.income);
  }

  void inflationCalculate(double incomeDiff, double turnover,  int base, int threshold){
    if (incomeDiff < base - threshold || incomeDiff > base + threshold) {
      this.inflation = (int) Math.ceil(150 / turnover * 100);
    }
  }

  public Double getIncome() {
    return this.income;
  }

  public Integer getInflation() {
    return this.inflation;
  }

  public void setInflation(int inflation) {
    this.inflation = inflation;
  }

  public void setIncome(double income) {
    this.income = income;
  }

  public BankDataProvider getProvider() {
    return this.provider;
  }

  public Integer getPrevInflation() {
    return this.prevInflation;
  }

  public void setSeller(Seller seller) {
    this.seller = seller;
  }

}
