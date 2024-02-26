package Observer.Publisher.Bank;

public class BankData {
  private int inflation;
  private int prevInflation;
  private double income;

  public BankData(int inflation) {
    this.inflation = inflation;
  }

  public BankData(int inflation, int prevInflation, double income) {
    this.inflation = inflation;
    this.prevInflation = prevInflation;
    this.income = income;
  }

  public int getInflation() {
    return inflation;
  }

  public void setInflation(int inflation) {
    if (inflation < 0) {
      throw new IllegalArgumentException("Inflation cannot be negative.");
    }
    this.inflation = inflation;
  }

  public int getPrevInflation() {
    return prevInflation;
  }

  public void setPrevInflation(int prevInflation) {
    if (prevInflation < 0) {
      throw new IllegalArgumentException("Previous inflation cannot be negative.");
    }
    this.prevInflation = prevInflation;
  }

  public double getIncome() {
    return this.income;
  }

  public void setIncome(double income) {
    this.income = income;
  }
}
