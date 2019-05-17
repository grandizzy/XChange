package org.knowm.xchange.ddex;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.ddex.dto.marketdata.DdexMarket;
import org.knowm.xchange.ddex.dto.marketdata.DdexMarkets;
import org.knowm.xchange.ddex.service.DdexMarketDataService;
import org.knowm.xchange.dto.meta.CurrencyMetaData;
import org.knowm.xchange.dto.meta.CurrencyPairMetaData;
import org.knowm.xchange.dto.meta.ExchangeMetaData;
import si.mazi.rescu.SynchronizedValueFactory;

public class DdexExchange extends BaseExchange {

  @Override
  protected void initServices() {
    this.marketDataService = new DdexMarketDataService(this);
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification =
        new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://api.ddex.io");
    exchangeSpecification.setHost("api.ddex.io");
    exchangeSpecification.setPort(80);
    exchangeSpecification.setExchangeName("DDex");
    exchangeSpecification.setExchangeDescription("DDex Exchange");

    return exchangeSpecification;
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return null;
  }

  @Override
  public void remoteInit() throws IOException {
    DdexMarkets markets = ((DdexMarketDataService) marketDataService).getDdexMarkets();
    exchangeMetaData = adaptToExchangeMetaData(exchangeMetaData, markets);
  }

  private ExchangeMetaData adaptToExchangeMetaData(
      ExchangeMetaData exchangeMetaData, DdexMarkets markets) {

    Map<CurrencyPair, CurrencyPairMetaData> currencyPairs = new HashMap<>();
    Map<Currency, CurrencyMetaData> currencies = new HashMap<>();
    for (DdexMarket market : markets.getMarkets()) {
      CurrencyPair pair = new CurrencyPair(market.getBaseToken(), market.getTargetToken());
      currencyPairs.put(pair, null);
    }
    return new ExchangeMetaData(currencyPairs, currencies, null, null, true);
  }
}
