package org.knowm.xchange.ddex.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DdexMarket {

  private final String id;
  private final String baseToken;
  private final String targetToken;
  private final Integer pricePrecision;

  public DdexMarket(
      @JsonProperty("id") String id,
      @JsonProperty("baseToken") String baseToken,
      @JsonProperty("quoteToken") String targetToken,
      @JsonProperty("pricePrecision") Integer pricePrecision) {

    this.id = id;
    this.baseToken = baseToken;
    this.targetToken = targetToken;
    this.pricePrecision = pricePrecision;
  }

  public String getId() {

    return id;
  }

  public String getBaseToken() {

    return baseToken;
  }

  public String getTargetToken() {

    return targetToken;
  }

  public Integer getPricePrecision() {

    return pricePrecision;
  }
}
