package com.microservices.eazybank.accounts.mapper;

import com.microservices.eazybank.accounts.entity.Accounts;
import com.microservices.eazybank.accounts.model.AccountsDTO;

public class AccountMapper {

  /**
   * @author: TranThanhChung-99
   * @since: 20/07/2024 21:08
   * @description: convert from AccountsDTO to Accounts
   * @param: Source accountsDTO , Target accounts
   * @returns: Target accounts
   */
  public static Accounts ConvertToAccounts(AccountsDTO accountsDTO, Accounts accounts) {
    accounts.setAccountType(accountsDTO.getAccountType());
    accounts.setBranchAddress(accountsDTO.getBranchAddress());
    accounts.setAccountNumber(accountsDTO.getAccountNumber());
    return accounts;
  }


  /**
   * @author: TranThanhChung-99
   * @since: 20/07/2024 21:08
   * @description: convert from Accounts to AccountsDTO
   * @param: Source accounts , Target accountsDTO
   * @returns: Target accountsDTO
   */
  public static AccountsDTO ConvertToAccountDTO(Accounts accounts, AccountsDTO accountsDTO) {
    accountsDTO.setAccountType(accounts.getAccountType());
    accountsDTO.setBranchAddress(accounts.getBranchAddress());
    accountsDTO.setAccountNumber(accounts.getAccountNumber());
    return accountsDTO;
  }
}
