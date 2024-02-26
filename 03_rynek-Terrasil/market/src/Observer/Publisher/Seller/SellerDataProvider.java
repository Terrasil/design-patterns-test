package Observer.Publisher.Seller;

import Visitor.Data.MarketItem;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;

public class SellerDataProvider implements Publisher<SellerData> {
  private List<Subscriber<? super SellerData>> subscribers;

  public SellerDataProvider() {
    subscribers = new ArrayList<>();
  }

  @Override
  public void subscribe(Subscriber<? super SellerData> subscriber) {
    if (!subscribers.contains(subscriber)) {
      subscribers.add(subscriber);
      subscriber.onSubscribe(new SellerUnSubscriber(subscribers, subscriber));
    }
  }

  private void measurementsChanged(List<MarketItem> items) {
    for (Subscriber<? super SellerData> subscriber : subscribers) {
      subscriber.onNext(new SellerData(items));
    }
  }

  public void setMeasurements(List<MarketItem> items) {
    measurementsChanged(items);
  }
}
