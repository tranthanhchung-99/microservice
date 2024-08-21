package com.microservices.eazybank.accounts.controller;

import com.microservices.eazybank.accounts.model.AccountsContactInfoRecord;
import com.microservices.eazybank.accounts.model.CustomerDTO;
import com.microservices.eazybank.accounts.model.ErrorResponseDTO;
import com.microservices.eazybank.accounts.model.ResponseDTO;
import com.microservices.eazybank.accounts.service.IAccountService;
import com.microservices.eazybank.accounts.utils.AccountConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(
    name = "Rest apis for accounts service",
    description = "provice CRUD about accounts of customer"
)
public class AccountController {

  private final IAccountService iAccountService;

  public AccountController(IAccountService iAccountService) {
    this.iAccountService = iAccountService;
  }

  @Autowired
  private Environment environment;

  @Autowired
  private AccountsContactInfoRecord accountsContactInfoRecord;


  @Value("${build.version}")
  private String buildVersion;

  @Operation(
      summary = "Create account",
      description = "Create a account for customer"
  )
  @ApiResponse(
      responseCode = "201",
      description = "HTTP status CREATED"
  )
  @PostMapping("/create")
  public ResponseEntity<ResponseDTO> createAccount(
      @Valid
      @RequestBody CustomerDTO customerDTO) {
    iAccountService.createAccount(customerDTO);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDTO(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
  }

  @Operation(
      summary = "Get info account",
      description = "Get info account & customer"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP status OK"
  )
  @GetMapping("/get-customer")
  public ResponseEntity<CustomerDTO> getCustomerByMobileNumber(
      @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
      @RequestParam String mobileNumber) {
    CustomerDTO customerDTO = iAccountService.getAccountByMobieNumber(mobileNumber);
    return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
  }


  @Operation(
      summary = "Update info ",
      description = "update info account & customer"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "HTTP status OK"
      ),
      @ApiResponse(
          responseCode = "417",
          description = "HTTP status EXPECTATION_FAILED"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP status INTERNAL_SERVER_ERROR",
          content = @Content(
              schema = @Schema(implementation = ErrorResponseDTO.class)
          )
      )}
  )

  @PutMapping("/update")
  public ResponseEntity<ResponseDTO> updateAccount(
      @Valid
      @RequestBody CustomerDTO customerDTO) {
    Boolean isUpdated = iAccountService.updateAccount(customerDTO);
    if (isUpdated) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDTO(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ResponseDTO(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
    }

  }

  @Operation(
      summary = "Delete info ",
      description = "delete info account & customer"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "HTTP status OK"
      ),
      @ApiResponse(
          responseCode = "417",
          description = "HTTP status EXPECTATION_FAILED"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP status INTERNAL_SERVER_ERROR",
          content = @Content(
              schema = @Schema(implementation = ErrorResponseDTO.class)
          )
      )}
  )
  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDTO> updateAccount(
      @RequestParam
      @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
      String mobileNumber) {
    Boolean isDeleted = iAccountService.deleteAccount(mobileNumber);
    if (isDeleted) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDTO(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseDTO(AccountConstants.STATUS_417,
              AccountConstants.MESSAGE_417_DELETE_FAIL));
    }

  }

  @Operation(
      summary = "Get info build ver",
      description = "Get info build version"
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
  @GetMapping("/build-info")
  public ResponseEntity<String> getBuildVerInfo() {
    return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
  }

  @Operation(
      summary = "Get info java ver",
      description = "Get info java version"
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
  @GetMapping("/java-version")
  public ResponseEntity<String> getJavaVerInfo() {
    return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
  }

  @Operation(
      summary = "Get contact info",
      description = "Get contact info"
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
  @GetMapping("/contact-info")
  public ResponseEntity<AccountsContactInfoRecord> getContactInfo() {
    return ResponseEntity.status(HttpStatus.OK).body(accountsContactInfoRecord);
  }
}
