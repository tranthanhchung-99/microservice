package com.microservices.eazybank.accounts;

import com.microservices.eazybank.accounts.model.AccountsContactInfoRecord;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableFeignClients
@EnableConfigurationProperties(value = AccountsContactInfoRecord.class)
@OpenAPIDefinition(
    info = @Info(
        title = "Account doc",
        description = "Document for account service",
        version = "V1",
        contact = @Contact(
            name = "TranThanhChung-99",
            email = "thanhchungt0@gmail.com",
            url = "https://www.myurl.com"
        ),
        license = @License(
            name = "name license",
            url = "https://my-license.com"
        )
    ),
    externalDocs = @ExternalDocumentation(
        description = "external doc for account service",
        url = "localhost:8080/swagger-ui/index.html"
    )
)
public class AccountsApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccountsApplication.class, args);
  }

}
