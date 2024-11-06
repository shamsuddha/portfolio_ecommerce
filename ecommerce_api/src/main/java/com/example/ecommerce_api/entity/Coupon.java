package com.example.ecommerce_api.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.hms.api.enums.DiscountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "coupon")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class, property="@json_mapping_id")
public class Coupon extends Auditable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @Column(name = "code", nullable = false)
  private  String code;

  @Column(length = 500)
  private  String description;

  @Column(name = "discount_type_key", nullable = false)
  @Enumerated(EnumType.STRING)
  private DiscountType discountTypeKey;
  @Column(name = "discount_type_value")
  private DiscountType discountTypeValue;

  @Column(name = "discount")
  private Double amount;

  @Column(name = "number_of_use", nullable = false)
  private Integer numberOfUse;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime startAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime endAt;

//  @Column(name = "enabled", nullable = false, columnDefinition = "boolean default false")
//  private boolean enabled;

  @JsonIgnore
  @OneToMany(mappedBy = "coupon", fetch = FetchType.LAZY)
  private List<Booking> bookingList;
}
