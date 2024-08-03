package com.microservices.eazybank.accounts.model;

import java.util.List;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "account")
public record AccountsContactInfoRecord(String message, Map<String, String> contactDetail,
                                        List<String> onCallSupport) {

}
