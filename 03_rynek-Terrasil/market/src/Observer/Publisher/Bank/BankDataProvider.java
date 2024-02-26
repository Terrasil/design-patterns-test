package Observer.Publisher.Bank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.SubmissionPublisher;

public class BankDataProvider extends SubmissionPublisher<BankData> {
  private List<Subscriber<? super BankData>> subscribers;

  public BankDataProvider() {
    subscribers = new ArrayList<>();
  }

  public void measurementsChanged(int inflation) {
    for (Subscriber<? super BankData> subscriber : subscribers) {
      subscriber.onNext(new BankData(inflation));
    }
  }

  public void setMeasurements(int inflation) {
    if (inflation < 0) { // negative inflation is a deflation
      throw new IllegalArgumentException("Inflation cannot be negative.");
    }
    measurementsChanged(inflation);
  }

  public void measurementsChanged(int inflation, int prevInflation, double income) {
    for (Subscriber<? super BankData> subscriber : subscribers) {
      subscriber.onNext(new BankData(inflation, prevInflation, income));
    }
  }

  public void setMeasurements(int inflation, int prevInflation, double income) {
    measurementsChanged(inflation, prevInflation, income);
  }

  @Override
  public void subscribe(Subscriber<? super BankData> subscriber) {
    subscribers.add(subscriber);
    super.subscribe(subscriber);
  }
}
