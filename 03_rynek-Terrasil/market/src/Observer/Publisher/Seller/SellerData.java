package Observer.Publisher.Seller;

import Visitor.Data.MarketItem;
import java.util.List;

public class SellerData {
  private List<MarketItem> items;

  public SellerData(List<MarketItem> items) {
    this.items = items;
  }

  public List<MarketItem> getItems() {
    return items;
  }

  public void setItems(List<MarketItem> items) {
    this.items = items;
  }
}
