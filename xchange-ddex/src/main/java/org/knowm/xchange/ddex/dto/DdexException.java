package org.knowm.xchange.ddex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import si.mazi.rescu.HttpStatusExceptionSupport;

public class DdexException extends HttpStatusExceptionSupport {

  public DdexException(@JsonProperty("message") String reason) {
    super(reason);
  }
}
