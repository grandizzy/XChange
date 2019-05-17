package org.knowm.xchange.ddex.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.knowm.xchange.ddex.dto.DdexResponse;

public class DdexMarkets extends DdexResponse {
  private final List<DdexMarket> markets;

  public DdexMarkets(@JsonProperty("data") Map<String, List<DdexMarket>> data) {
    this.markets = data.get("markets");
  }

  public List<DdexMarket> getMarkets() {
    return markets;
  }
}
