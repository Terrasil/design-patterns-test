package Visitor.Visitors;

import Visitor.Data.Bike;
import Visitor.Data.Rollers;
import Visitor.Data.Scooter;

public interface MarketVisitor {
  double visit(Bike bike);

  double visit(Scooter scooter);

  double visit(Rollers rollers);
}