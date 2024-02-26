package Visitor.Visitors;

import Visitor.Data.Bike;
import Visitor.Data.Rollers;
import Visitor.Data.Scooter;

public class MarketVisitorImpl implements MarketVisitor {

  @Override
  public double visit(Bike bike) {
    return bike.getCost() + bike.getCost() * bike.getMargin() / 100;
  }

  @Override
  public double visit(Scooter scooter) {
    return scooter.getCost() + scooter.getCost() * scooter.getMargin() / 100;
  }

  @Override
  public double visit(Rollers rollers) {
    return rollers.getCost() + rollers.getCost() * rollers.getMargin() / 100;
  }
}
