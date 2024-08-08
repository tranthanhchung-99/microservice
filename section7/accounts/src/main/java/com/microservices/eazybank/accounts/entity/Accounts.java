package com.microservices.eazybank.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: TranThanhChung-99
 * @since: 18/07/2024 22:32
 * @description: Accounts entity
 */
@Entity
@Table(name = "accounts")
@Setter
@Getter
public class Accounts extends BaseEntity {

  @Column(name = "customer_id")
  private Long customerId;
  @Id
  @Column(name = "account_number")
  private Long accountNumber;
  @Column(name = "account_type")
  private String accountType;
  @Column(name = "branch_address")
  private String branchAddress;

}
