package com.microservices.eazybank.accounts.service;

import com.microservices.eazybank.accounts.model.CustomerdetailDto;

/**
 * @author: TranThanhChung-99
 * @since: 09/08/2024 20:58
 * @description: IService for get info customer
 */
public interface ICustomerDetailsService {

  /**
   * @author: TranThanhChung-99
   * @since: 09/08/2024 20:58
   * @description: get info account , card, loan of customer
   * @param: String mobileNumber
   * @returns: CustomerdetailDto  CustomerdetailDto
   */
  CustomerdetailDto fetchDetailsCustomer(String mobileNumber);
}
