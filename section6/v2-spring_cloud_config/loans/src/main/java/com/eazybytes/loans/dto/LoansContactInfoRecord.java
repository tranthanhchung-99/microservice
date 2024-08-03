package com.eazybytes.loans.dto;

import java.util.List;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("loans")
public record LoansContactInfoRecord(String message , Map<String,String> contactDetails , List<String> onSupportCall) {

}
