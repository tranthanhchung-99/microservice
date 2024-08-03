package com.microservices.eazybank.accounts.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
    name = "Account",
    description = "Accounts info of customer"
)
public class AccountsDTO {

  @Schema(
      description = "Account number of customer",
      example = "0030040050"
  )
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits.")
  private Long accountNumber;

  @Schema(
      description = "Account type of customer",
      example = "Savings"
  )
  @NotEmpty(message = "Account type can not be a null or empty.")
  private String accountType;

  @Schema(
      description = "Branch address account info of customer",
      example = "1 ngo 132/28 Dai Linh"
  )
  @NotEmpty(message = "Branch address can not be a null or empty.")
  private String branchAddress;
}
