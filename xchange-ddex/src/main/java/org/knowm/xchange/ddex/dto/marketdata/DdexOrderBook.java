package org.knowm.xchange.ddex.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.knowm.xchange.ddex.dto.DdexResponse;

public class DdexOrderBook {
  private final List<DdexOrder> asks;
  private final List<DdexOrder> bids;
  private final String marketId;

  public DdexOrderBook(
      @JsonProperty("marketId") String marketId,
      @JsonProperty("asks") List<DdexOrder> asks,
      @JsonProperty("bids") List<DdexOrder> bids) {
    this.asks = asks;
    this.bids = bids;
    this.marketId = marketId;
  }

  public List<DdexOrder> getAsks() {
    return asks;
  }

  public List<DdexOrder> getBids() {
    return bids;
  }

  public String getMarketId() {
    return marketId;
  }

  @Override
  public String toString() {
    return "DdexOrderBook{" + "asks=" + asks + ", bids=" + bids + '}';
  }

  public static class DdexOrderBookResponse extends DdexResponse {
    private final DdexOrderBook orderBook;

    public DdexOrderBookResponse(@JsonProperty("data") Map<String, DdexOrderBook> data) {
      this.orderBook = data.get("orderBook");
    }

    public DdexOrderBook getOrderBook() {
      return orderBook;
    }
  }
}
