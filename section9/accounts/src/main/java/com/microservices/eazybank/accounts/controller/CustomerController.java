package com.microservices.eazybank.accounts.controller;

import com.microservices.eazybank.accounts.model.CustomerdetailDto;
import com.microservices.eazybank.accounts.model.ErrorResponseDTO;
import com.microservices.eazybank.accounts.service.ICustomerDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(
    name = "Rest apis for customer service",
    description = "provice info about accounts, cards, loans of customer"
)
public class CustomerController {

  private final ICustomerDetailsService iCustomerDetailsService;
  private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  public CustomerController(ICustomerDetailsService iCustomerDetailsService) {
    this.iCustomerDetailsService = iCustomerDetailsService;
  }

  @Operation(
      summary = "Get info customer",
      description = "Get info account, loan, card of customer"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "HTTP status OK"
      )
      ,
      @ApiResponse(
          responseCode = "500",
          description = "HTTP status INTERNAL_SERVER_ERROR",
          content = @Content(
              schema = @Schema(implementation = ErrorResponseDTO.class)
          )
      )
  })
  @GetMapping("/fetchCustomerDetails")
  public ResponseEntity<CustomerdetailDto> fetchCustomer(
      @RequestHeader("eazybank-correlation-id") String correlationId,
      @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
      String mobileNumber) {
    logger.debug("eazyBank-correlation-id found: {} ", correlationId);
    CustomerdetailDto customerdetailDto = iCustomerDetailsService.fetchDetailsCustomer(
        correlationId, mobileNumber);

    return ResponseEntity.status(HttpStatus.OK).body(customerdetailDto);
  }
}
