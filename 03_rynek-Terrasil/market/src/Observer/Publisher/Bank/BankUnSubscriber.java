package Observer.Publisher.Bank;

import java.util.List;
import java.util.concurrent.Flow.Subscriber;

public class BankUnSubscriber implements AutoCloseable {
  private List<Subscriber<? super BankData>> subscribers;
  private Subscriber<? super BankData> subscriber;

  public BankUnSubscriber(List<Subscriber<? super BankData>> subscribers,
      Subscriber<? super BankData> subscriber) {
    this.subscribers = subscribers;
    this.subscriber = subscriber;
  }

  @Override
  public void close() {
    if (subscriber != null && subscribers.contains(subscriber)) {
      subscribers.remove(subscriber);
    }
  }
}
