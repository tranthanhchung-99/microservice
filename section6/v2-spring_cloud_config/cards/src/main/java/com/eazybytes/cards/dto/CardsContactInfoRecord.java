package com.eazybytes.cards.dto;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cards")
@Getter
@Setter
public class CardsContactInfoRecord {
  private String message;
  private Map<String,String> contactDetails;
  private List<String> onSupportCall;

}
