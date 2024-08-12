package com.microservices.eazybank.accounts.service.impl;

import com.microservices.eazybank.accounts.entity.Accounts;
import com.microservices.eazybank.accounts.entity.Customer;
import com.microservices.eazybank.accounts.exception.ResourceNotFoundException;
import com.microservices.eazybank.accounts.mapper.AccountMapper;
import com.microservices.eazybank.accounts.mapper.CustomerMapper;
import com.microservices.eazybank.accounts.model.AccountsDTO;
import com.microservices.eazybank.accounts.model.CardsDto;
import com.microservices.eazybank.accounts.model.CustomerdetailDto;
import com.microservices.eazybank.accounts.model.LoansDto;
import com.microservices.eazybank.accounts.repository.AccountsRepository;
import com.microservices.eazybank.accounts.repository.CustomerRepository;
import com.microservices.eazybank.accounts.service.ICustomerDetailsService;
import com.microservices.eazybank.accounts.service.client.CardsFeignClient;
import com.microservices.eazybank.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerDetailsServiceImpl implements ICustomerDetailsService {

  private AccountsRepository accountsRepository;
  private CustomerRepository customerRepository;
  private CardsFeignClient cardsFeignClient;
  private LoansFeignClient loansFeignClient;

  /**
   * @param mobileNumber
   * @author: TranThanhChung-99
   * @since: 09/08/2024 20:58
   * @description: get info account , card, loan of customer
   * @param: String mobileNumber
   * @returns: CustomerdetailDto  CustomerdetailDto
   */
  @Override
  public CustomerdetailDto fetchDetailsCustomer(String mobileNumber) {
    Customer customer = customerRepository.findByMobileNumber(mobileNumber)
        .orElseThrow(
            () -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber)
        );
    Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
        () -> new ResourceNotFoundException("Account", "customer ID",
            customer.getCustomerId().toString())
    );

    CustomerdetailDto customerdetailDto = CustomerMapper.ConvertToCustomerDetailsDTO(customer,
        new CustomerdetailDto());
    customerdetailDto.setAccountsDTO(
        AccountMapper.ConvertToAccountDTO(accounts, new AccountsDTO()));
    ResponseEntity<LoansDto> loansDto = loansFeignClient.fetchLoanDetails(mobileNumber);
    ResponseEntity<CardsDto> cardsDto = cardsFeignClient.fetchCardDetails(mobileNumber);
    customerdetailDto.setCardsDto(cardsDto.getBody());
    customerdetailDto.setLoansDto(loansDto.getBody());
    return customerdetailDto;
  }
}
