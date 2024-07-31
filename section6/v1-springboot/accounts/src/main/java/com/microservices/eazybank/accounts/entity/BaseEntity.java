package com.microservices.eazybank.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author: TranThanhChung-99
 * @since: 18/07/2024 21:49
 * @description: Class base for project auto create data for fields(createdBy, createdAt,updatedBy,
 * updatedAt)
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

  @CreatedBy
  @Column(updatable = false)
  private String createdBy;

  @LastModifiedBy
  @Column(insertable = false)
  private String updatedBy;

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime updatedAt;
}
