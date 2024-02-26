package Visitor.Data;

import Visitor.Visitors.MarketVisitor;

public class Scooter extends MarketItem {
  public Scooter(String name, double cost, int margin, int amount) {
    if (cost < 0 || margin < 0 || amount < 0) {
      throw new IllegalArgumentException();
    }
    this.setName(name);
    this.setCost(cost);
    this.setMargin(margin);
    this.setAmount(amount);
    this.setPrevMargin(0);
    this.setSold(0);
  }

  @Override
  public double accept(MarketVisitor visitor) {
    return visitor.visit(this);
  }
}