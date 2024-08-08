package com.microservices.eazybank.accounts.model;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "account")
@Getter
@Setter
public class
AccountsContactInfoRecord {

  private String message;
  private Map<String, String> contactDetail;
  private List<String> onCallSupport;
}
