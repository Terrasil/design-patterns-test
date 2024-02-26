package Observer.Subscriber;

import Observer.Publisher.Bank.BankData;
import Visitor.Seller;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Publisher;

public class SellerBankSubscriber implements Subscriber<BankData> {
  private BankData data;
  private Subscription subscription;
  private Seller seller;

  public SellerBankSubscriber() {
  }

  public SellerBankSubscriber(Publisher<BankData> provider) {
    provider.subscribe(this);
  }

  public void subscribe(Publisher<BankData> provider, Seller seller) {
    if (subscription == null) {
      provider.subscribe(this);
    }
    this.seller = seller;
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
  public void onNext(BankData item) {
    this.data = item;
  }

  @Override
  public void onError(Throwable throwable) {

  }

  @Override
  public void onComplete() {

  }

  public BankData getData() {
    return data;
  }

  public Seller getSeller() {
    return seller;
  }
}
