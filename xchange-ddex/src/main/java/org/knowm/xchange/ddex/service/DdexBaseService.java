package org.knowm.xchange.ddex.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ddex.Ddex;
import org.knowm.xchange.ddex.dto.DdexException;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.FundsExceededException;
import org.knowm.xchange.exceptions.InternalServerException;
import org.knowm.xchange.exceptions.RateLimitExceededException;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.RestProxyFactory;

public class DdexBaseService extends BaseExchangeService implements BaseService {

  protected final Ddex ddex;

  protected DdexBaseService(Exchange exchange) {

    super(exchange);
    ddex =
        RestProxyFactory.createProxy(
            Ddex.class, exchange.getExchangeSpecification().getSslUri(), getClientConfig());
  }

  protected ExchangeException handleError(DdexException exception) {

    if (exception.getMessage().contains("Insufficient")) {
      return new FundsExceededException(exception);
    } else if (exception.getMessage().contains("Rate limit exceeded")) {
      return new RateLimitExceededException(exception);
    } else if (exception.getMessage().contains("Internal server error")) {
      return new InternalServerException(exception);
    } else {
      return new ExchangeException(exception);
    }
  }
}
