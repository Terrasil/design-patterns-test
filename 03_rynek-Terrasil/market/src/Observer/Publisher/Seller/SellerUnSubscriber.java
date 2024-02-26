package Observer.Publisher.Seller;

import java.util.List;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.Flow.Subscriber;

public class SellerUnSubscriber implements Subscription {
  private List<Subscriber<? super SellerData>> subscribers;
  private Subscriber<? super SellerData> subscriber;

  public SellerUnSubscriber(List<Subscriber<? super SellerData>> subscribers,
      Subscriber<? super SellerData> subscriber) {
    this.subscribers = subscribers;
    this.subscriber = subscriber;
  }

  @Override
  public void request(long n) {
  }

  @Override
  public void cancel() {
    if (subscriber != null && subscribers.contains(subscriber)) {
      subscribers.remove(subscriber);
    }
  }
}
