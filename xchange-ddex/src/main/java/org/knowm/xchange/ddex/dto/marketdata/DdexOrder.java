package org.knowm.xchange.ddex.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class DdexOrder {
  private final BigDecimal price;
  private final BigDecimal amount;

  public DdexOrder(
      @JsonProperty("price") BigDecimal price, @JsonProperty("amount") BigDecimal amount) {
    this.price = price;
    this.amount = amount;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    return "DdexOrder{" + "price=" + price + ", amount=" + amount + '}';
  }
}
