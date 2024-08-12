package com.microservices.eazybank.accounts.service.client;

import com.microservices.eazybank.accounts.model.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansFeignClient {

  @GetMapping(value = "/api/fetch", consumes = "application/json")
  public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam String mobileNumber);
}
