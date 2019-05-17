package org.knowm.xchange.ddex.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Map;
import org.knowm.xchange.ddex.dto.DdexResponse;

public class DdexMarketTicker {

  private final String marketId;
  private final BigDecimal price;
  private final BigDecimal bid;
  private final BigDecimal ask;
  private final BigDecimal high;
  private final BigDecimal low;
  private final BigDecimal volume;
  private final Float updatedAt;

  public DdexMarketTicker(
      @JsonProperty("marketId") String marketId,
      @JsonProperty("price") BigDecimal price,
      @JsonProperty("volume") BigDecimal volume,
      @JsonProperty("bid") BigDecimal bid,
      @JsonProperty("ask") BigDecimal ask,
      @JsonProperty("low") BigDecimal low,
      @JsonProperty("high") BigDecimal high,
      @JsonProperty("updatedAt") Float updatedAt) {

    this.marketId = marketId;
    this.price = price;
    this.bid = bid;
    this.ask = ask;
    this.volume = volume;
    this.updatedAt = updatedAt;
    this.high = high;
    this.low = low;
  }

  public String getMarketId() {
    return marketId;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getBid() {
    return bid;
  }

  public BigDecimal getAsk() {
    return ask;
  }

  public BigDecimal getVolume() {
    return volume;
  }

  public BigDecimal getHigh() {
    return high;
  }

  public BigDecimal getLow() {
    return low;
  }

  public Float getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public String toString() {
    return "DdexMarketTicker [marketId="
        + marketId
        + ", price="
        + price
        + ", bid="
        + bid
        + ", ask="
        + ask
        + ", volume="
        + volume
        + ", volume="
        + low
        + ", low="
        + high
        + ", high="
        + updatedAt
        + "]";
  }

  public static class DdexMarketTickerResponse extends DdexResponse {
    private final DdexMarketTicker ticker;

    public DdexMarketTickerResponse(@JsonProperty("data") Map<String, DdexMarketTicker> data) {
      this.ticker = data.get("ticker");
    }

    public DdexMarketTicker getTicker() {
      return ticker;
    }
  }
}
