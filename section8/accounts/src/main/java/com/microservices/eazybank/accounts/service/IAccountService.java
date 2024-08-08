package com.microservices.eazybank.accounts.service;

import com.microservices.eazybank.accounts.model.CustomerDTO;

public interface IAccountService {

  /**
   * @author: TranThanhChung-99
   * @since: 20/07/2024 20:25
   * @description: create account for customer.
   * @param: CustomerDTO
   * @returns: void
   */
  void createAccount(CustomerDTO customerDTO);

  /**
   * @author: TranThanhChung-99
   * @since: 21/07/2024 20:39
   * @description: get customer info by mobile number
   * @param: String mobileNumber
   * @returns: CustomerDTO
   */
  CustomerDTO getAccountByMobieNumber(String mobileNumber);


  boolean updateAccount(CustomerDTO customerDTO);

  boolean deleteAccount(String mobileNumber);
}
