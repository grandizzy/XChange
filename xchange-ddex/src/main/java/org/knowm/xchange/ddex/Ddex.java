package org.knowm.xchange.ddex;

import java.io.IOException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.knowm.xchange.ddex.dto.DdexException;
import org.knowm.xchange.ddex.dto.marketdata.DdexMarketTicker;
import org.knowm.xchange.ddex.dto.marketdata.DdexMarkets;
import org.knowm.xchange.ddex.dto.marketdata.DdexOrderBook;

@Path("v3")
@Produces(MediaType.APPLICATION_JSON)
public interface Ddex {

  @GET
  @Path("markets")
  DdexMarkets getMarkets() throws DdexException, IOException;

  @GET
  @Path("markets/{baseToken}-{targetToken}/ticker")
  DdexMarketTicker.DdexMarketTickerResponse getMarketTicker(
      @PathParam("baseToken") String baseToken, @PathParam("targetToken") String targetToken)
      throws DdexException, IOException;

  @GET
  @Path("markets/{baseToken}-{targetToken}/orderbook")
  DdexOrderBook.DdexOrderBookResponse getOrderBook(
      @PathParam("baseToken") String baseToken,
      @PathParam("targetToken") String targetToken,
      @QueryParam("level") Integer size)
      throws DdexException, IOException;
}
