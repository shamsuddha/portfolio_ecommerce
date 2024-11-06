package com.example.ecommerce_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class, property="@json_mapping_id")
public class Auditable {

  @CreatedBy
  @Column(name = "created_by", updatable = false)
  protected String createdBy;

  @CreatedDate
  //@Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", updatable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  protected LocalDateTime createdDate;

  @LastModifiedBy
  @Column(name = "update_by", insertable = false)
  protected String lastModifiedBy;

  @LastModifiedDate
  //@Temporal(TemporalType.TIMESTAMP)
  @Column(name = "update_at")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  protected LocalDateTime lastModifiedDate;

  @Column(name = "enabled", nullable = false, columnDefinition = "boolean default true")
  protected Boolean enabled = true;
}
