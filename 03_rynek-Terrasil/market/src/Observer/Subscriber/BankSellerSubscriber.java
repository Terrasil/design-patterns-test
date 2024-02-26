package Observer.Subscriber;

import Observer.Publisher.Seller.SellerData;
import Visitor.Bank;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Publisher;

public class BankSellerSubscriber implements Subscriber<SellerData> {
  private SellerData data;
  private Subscription subscription;
  private Bank bank;

  public BankSellerSubscriber() {
  }

  public BankSellerSubscriber(Publisher<SellerData> provider) {
    provider.subscribe(this);
  }

  public void subscribe(Publisher<SellerData> provider, Bank bank) {
    if (subscription == null) {
      provider.subscribe(this);
    }
    this.bank = bank;
  }

  public void unsubscribe() {
    subscription.cancel();
  }

  @Override
  public void onSubscribe(Subscription subscription) {
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

  public Bank getBank() {
    return bank;
  }
}
