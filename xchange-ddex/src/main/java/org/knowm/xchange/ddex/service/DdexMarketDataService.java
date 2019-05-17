package org.knowm.xchange.ddex.service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.ddex.dto.DdexException;
import org.knowm.xchange.ddex.dto.marketdata.DdexMarketTicker;
import org.knowm.xchange.ddex.dto.marketdata.DdexMarkets;
import org.knowm.xchange.ddex.dto.marketdata.DdexOrder;
import org.knowm.xchange.ddex.dto.marketdata.DdexOrderBook;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class DdexMarketDataService extends DdexBaseService implements MarketDataService {

  public DdexMarketDataService(Exchange exchange) {
    super(exchange);
  }

  public DdexMarkets getDdexMarkets() throws IOException {
    try {
      return ddex.getMarkets();
    } catch (DdexException e) {
      throw handleError(e);
    }
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {
    DdexMarketTicker ddexTicker = getDdexMarketTicker(currencyPair);

    return new Ticker.Builder()
        .currencyPair(currencyPair)
        .bid(ddexTicker.getBid())
        .ask(ddexTicker.getAsk())
        .high(ddexTicker.getHigh())
        .low(ddexTicker.getLow())
        .volume(ddexTicker.getVolume())
        .build();
  }

  public DdexMarketTicker getDdexMarketTicker(CurrencyPair currencyPair) throws IOException {

    if (!checkProductExists(currencyPair)) {
      return null;
    }
    try {
      DdexMarketTicker tickerReturn =
          ddex.getMarketTicker(
                  currencyPair.base.getCurrencyCode(), currencyPair.counter.getCurrencyCode())
              .getTicker();
      return tickerReturn;
    } catch (DdexException e) {
      throw handleError(e);
    }
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {

    Integer limit = null;

    if (args != null && args.length > 0) {
      if (args[0] instanceof Integer && (Integer) args[0] > 0) {
        limit = (Integer) args[0];
      }
    }
    return adaptOrderBook(
        ddex.getOrderBook(
            currencyPair.base.getCurrencyCode(), currencyPair.counter.getCurrencyCode(), limit),
        currencyPair);
  }

  private OrderBook adaptOrderBook(
      DdexOrderBook.DdexOrderBookResponse response, CurrencyPair currencyPair) {

    DdexOrderBook orders = response.getOrderBook();
    List<LimitOrder> asks = new LinkedList<>();
    orders
        .getAsks()
        .forEach(order -> asks.add(adaptOrder(currencyPair, Order.OrderType.ASK, order)));
    List<LimitOrder> bids = new LinkedList<>();
    orders
        .getBids()
        .forEach(order -> bids.add(adaptOrder(currencyPair, Order.OrderType.BID, order)));
    return new OrderBook(null, asks, bids);
  }

  private static LimitOrder adaptOrder(
      CurrencyPair currencyPair, Order.OrderType orderType, DdexOrder order) {

    return new LimitOrder(orderType, order.getAmount(), currencyPair, null, null, order.getPrice());
  }

  public boolean checkProductExists(CurrencyPair currencyPair) {

    boolean currencyPairSupported = false;
    for (CurrencyPair cp : exchange.getExchangeSymbols()) {
      if (cp.base.getCurrencyCode().equalsIgnoreCase(currencyPair.base.getCurrencyCode())
          && cp.counter
              .getCurrencyCode()
              .equalsIgnoreCase(currencyPair.counter.getCurrencyCode())) {

        currencyPairSupported = true;
        break;
      }
    }

    return currencyPairSupported;
  }
}
