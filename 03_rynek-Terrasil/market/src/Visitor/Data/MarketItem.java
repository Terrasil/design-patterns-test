package Visitor.Data;

import Visitor.Visitors.MarketVisitor;

public abstract class MarketItem {
  private double cost;
  private int margin;
  private int amount;
  private int sold;
  private int prevMargin;
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    if (cost < 0) {
      throw new IllegalArgumentException();
    }
    this.cost = cost;
  }

  public int getMargin() {
    return margin;
  }

  public void setMargin(int margin) {
    if (margin < 0) {
      throw new IllegalArgumentException();
    }
    this.margin = margin;
  }

  public int getPrevMargin() {
    return prevMargin;
  }

  public void setPrevMargin(int prevMargin) {
    if (prevMargin < 0) {
      throw new IllegalArgumentException();
    }
    this.prevMargin = prevMargin;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException();
    }
    this.amount = amount;
  }

  public int getSold() {
    return sold;
  }

  public void setSold(int sold) {
    if (sold < 0) {
      throw new IllegalArgumentException();
    }
    this.sold = sold;
  }

  public abstract double accept(MarketVisitor visitor);

  @Override
  public String toString() {
    return "MarketItem{" +
        "cost=" + cost +
        ", margin=" + margin +
        ", amount=" + amount +
        ", sold=" + sold +
        ", prevMargin=" + prevMargin +
        ", name='" + name + '\'' +
        '}';
  }
}