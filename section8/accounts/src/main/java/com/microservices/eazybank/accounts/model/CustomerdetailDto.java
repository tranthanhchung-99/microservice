package com.microservices.eazybank.accounts.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    name = "CustomerDetail",
    description = " Accounts, Cards, Loans of customer info"
)
public class CustomerdetailDto {

  @Schema(
      description = "Name of customer",
      example = "TranThanhChung"
  )
  @NotEmpty(message = "Name can not be a null or empty.")
  @Size(min = 5, max = 30, message = "This length of customer'name should be between 5 and 30.")
  private String name;

  @Schema(
      description = "Email address of customer",
      example = "thanhchungt0000@gmail.com"
  )
  @NotEmpty(message = "Email address can not be a null or empty.")
  @Email(message = "Email address should be a valid value.")
  private String email;

  @Schema(
      description = "Mobile number of customer",
      example = "0942358876"
  )
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
  private String mobileNumber;

  @Schema(
      description = "Account info of customer"
  )
  private AccountsDTO accountsDTO;

  @Schema(
      description = "Card info of customer"
  )
  private CardsDto cardsDto;

  @Schema(
      description = "Loan info of customer"
  )
  private LoansDto loansDto;
}
