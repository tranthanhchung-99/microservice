package com.microservices.eazybank.accounts.service.impl;

import com.microservices.eazybank.accounts.entity.Accounts;
import com.microservices.eazybank.accounts.entity.Customer;
import com.microservices.eazybank.accounts.exception.CustomerAlreadyExistsException;
import com.microservices.eazybank.accounts.exception.ResourceNotFoundException;
import com.microservices.eazybank.accounts.mapper.AccountMapper;
import com.microservices.eazybank.accounts.mapper.CustomerMapper;
import com.microservices.eazybank.accounts.model.AccountsDTO;
import com.microservices.eazybank.accounts.model.CustomerDTO;
import com.microservices.eazybank.accounts.repository.AccountsRepository;
import com.microservices.eazybank.accounts.repository.CustomerRepository;
import com.microservices.eazybank.accounts.service.IAccountService;
import com.microservices.eazybank.accounts.utils.AccountConstants;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

  private AccountsRepository accountsRepository;

  private CustomerRepository customerRepository;

  /**
   * @author: TranThanhChung-99
   * @since: 20/07/2024 21:21
   * @description: service for create accounts
   * @param: CustomerDTO customerDTO
   * @returns: void
   */
  @Override
  public void createAccount(CustomerDTO customerDTO) {
    Customer customer = CustomerMapper.ConvertToCustomer(customerDTO, new Customer());
    Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(
        customerDTO.getMobileNumber());
    if (optionalCustomer.isPresent()) {
      throw new CustomerAlreadyExistsException(
          "Customer already registered with given mobileNumber "
              + customerDTO.getMobileNumber());
    }
    Customer savedCustomer = customerRepository.save(customer);
    accountsRepository.save(createNewAccount(savedCustomer));
  }

  /**
   * @param mobileNumber
   * @author: TranThanhChung-99
   * @since: 21/07/2024 20:39
   * @description: get customer info by mobile number
   * @param: String mobileNumber
   * @returns: CustomerDTO
   */
  @Override
  public CustomerDTO getAccountByMobieNumber(String mobileNumber) {
    Customer customer = customerRepository.findByMobileNumber(mobileNumber)
        .orElseThrow(
            () -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber)
        );
    Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
        () -> new ResourceNotFoundException("Account", "customer ID",
            customer.getCustomerId().toString())
    );

    CustomerDTO customerDTO = CustomerMapper.ConvertToCustomerDTO(customer, new CustomerDTO());
    AccountsDTO accountsDTO = AccountMapper.ConvertToAccountDTO(accounts, new AccountsDTO());
    customerDTO.setAccountsDTO(accountsDTO);
    return customerDTO;
  }

  @Override
  public boolean updateAccount(CustomerDTO customerDTO) {
    boolean isUpdated = false;
    AccountsDTO accountsDto = customerDTO.getAccountsDTO();
    if (accountsDto != null) {
      Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
          () -> new ResourceNotFoundException("Account", "AccountNumber",
              accountsDto.getAccountNumber().toString())
      );
      AccountMapper.ConvertToAccounts(accountsDto, accounts);
      accounts = accountsRepository.save(accounts);

      Long customerId = accounts.getCustomerId();
      Customer customer = customerRepository.findById(customerId).orElseThrow(
          () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
      );
      CustomerMapper.ConvertToCustomer(customerDTO, customer);
      customerRepository.save(customer);
      isUpdated = true;
    }
    return isUpdated;
  }

  @Override
  public boolean deleteAccount(String mobileNumber) {
    Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
        () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
    );
    accountsRepository.deleteByCustomerId(customer.getCustomerId());
    customerRepository.deleteById(customer.getCustomerId());
    return true;
  }

  private Accounts createNewAccount(Customer customer) {
    Accounts newAccount = new Accounts();
    newAccount.setCustomerId(customer.getCustomerId());
    long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

    newAccount.setAccountNumber(randomAccNumber);
    newAccount.setAccountType(AccountConstants.SAVINGS);
    newAccount.setBranchAddress(AccountConstants.ADDRESS);
    return newAccount;
  }
}
