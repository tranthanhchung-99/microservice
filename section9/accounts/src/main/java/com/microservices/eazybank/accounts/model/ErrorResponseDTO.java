package com.microservices.eazybank.accounts.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Schema(
    name = "Error response",
    description = "Error response info"
)
public class ErrorResponseDTO {

  @Schema(
      description = "Api path of response",
      example = "/api/create"
  )
  private String apiPath;

  @Schema(
      description = "Error code of response",
      example = "400"
  )
  private HttpStatus errorCode;

  @Schema(
      description = "Error message of response",
      example = "Fail!"
  )
  private String errorMsg;

  @Schema(
      description = "Time occurs error",
      example = "2007-12-03T10:15:30."
  )
  private LocalDateTime errorTime;
}
