package com.eazybytes.cards.dto;

import java.util.List;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cards")
public record CardsContactInfoRecord(String message, Map<String,String> contactDetails, List<String> onSupportCall) {

}
