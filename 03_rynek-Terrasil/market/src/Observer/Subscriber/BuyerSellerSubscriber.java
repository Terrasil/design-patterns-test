package Observer.Subscriber;

import Observer.Publisher.Seller.SellerData;
import Visitor.Buyer;

import java.util.concurrent.Flow;

public class BuyerSellerSubscriber implements Flow.Subscriber<SellerData> {
  private SellerData data;
  private Flow.Subscription subscription;
  private Buyer buyer;

  public BuyerSellerSubscriber() {
  }

  public BuyerSellerSubscriber(Flow.Publisher<SellerData> provider) {
    provider.subscribe(this);
  }

  public void subscribe(Flow.Publisher<SellerData> provider, Buyer buyer) {
    if (subscription == null) {
      provider.subscribe(this);
    }
    this.buyer = buyer;
  }

  public void unsubscribe() {
    subscription.cancel();
  }

  @Override
  public void onSubscribe(Flow.Subscription subscription) {
    this.subscription = subscription;
    subscription.request(Long.MAX_VALUE);
  }

  @Override
  public void onNext(SellerData item) {
    this.data = item;
  }

  @Override
  public void onError(Throwable throwable) {

  }

  @Override
  public void onComplete() {

  }

  public SellerData getData() {
    return data;
  }

  public Buyer getBuyer() {
    return buyer;
  }
}
