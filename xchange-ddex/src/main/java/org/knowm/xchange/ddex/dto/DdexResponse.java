package org.knowm.xchange.ddex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DdexResponse {
  @JsonProperty("status")
  private String status;

  @JsonProperty("desc")
  private String description;

  public String getStatus() {
    return status;
  }

  public String getDescription() {
    return description;
  }
}
