package com.microservices.eazybank.accounts.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: TranThanhChung-99
 * @since: 20/07/2024 21:48
 * @description:
 */
@Data
@AllArgsConstructor
@Schema(
    name = "Response",
    description = "Response info"
)
public class ResponseDTO {

  @Schema(
      description = "http status code"

  )
  private String statusCode;

  @Schema(
      description = "Message of response"
  )
  private String statusMsg;
}
