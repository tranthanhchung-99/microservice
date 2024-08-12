package com.microservices.eazybank.accounts.mapper;

import com.microservices.eazybank.accounts.entity.Customer;
import com.microservices.eazybank.accounts.model.CustomerDTO;
import com.microservices.eazybank.accounts.model.CustomerdetailDto;

public class CustomerMapper {

  /**
   * @author: TranThanhChung-99
   * @since: 20/07/2024 21:08
   * @description: convert from CustomerDTO to Customer
   * @param: Source customerDTO , Target customer
   * @returns: Target customer
   */
  public static Customer ConvertToCustomer(CustomerDTO customerDTO, Customer customer) {
    customer.setName(customerDTO.getName());
    customer.setEmail(customerDTO.getEmail());
    customer.setMobileNumber(customerDTO.getMobileNumber());
    return customer;
  }


  /**
   * @author: TranThanhChung-99
   * @since: 20/07/2024 21:08
   * @description: convert from Customer to CustomerDTO
   * @param: Source customer , Target customerDTO
   * @returns: Target customerDTO
   */
  public static CustomerDTO ConvertToCustomerDTO(Customer customer, CustomerDTO customerDTO) {
    customerDTO.setName(customer.getName());
    customerDTO.setEmail(customer.getEmail());
    customerDTO.setMobileNumber(customer.getMobileNumber());
    return customerDTO;
  }

  /**
   * @author: TranThanhChung-99
   * @since: 20/07/2024 21:08
   * @description: convert from Customer to customerdetailDto
   * @param: Source customer , Target customerdetailDto
   * @returns: Target customerdetailDto
   */
  public static CustomerdetailDto ConvertToCustomerDetailsDTO(Customer customer,
      CustomerdetailDto customerdetailDto) {
    customerdetailDto.setName(customer.getName());
    customerdetailDto.setEmail(customer.getEmail());
    customerdetailDto.setMobileNumber(customer.getMobileNumber());
    return customerdetailDto;
  }
}
