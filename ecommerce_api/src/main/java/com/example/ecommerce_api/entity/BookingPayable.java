package com.example.ecommerce_api.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.hms.api.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "booking_payable")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class, property="@json_mapping_id")
public class BookingPayable extends Auditable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "booking_id", foreignKey = @ForeignKey(name = "fk_booking_payable_booking_id"))
  private Booking booking;

  @Column(name = "booking_id", insertable = false, updatable = false)
  private String bookingId;

  @Column(name = "amount")
  private Double amount;

  @Column(name="head")
  private String head; //todo head should be an enum or table

  @Column(name = "discount_percentage")
  private Double discountPercentage;

  @Column(name = "discount_amount")
  private Double discountAmount;

  @Column(name = "final_amount")
  private Double finalAmount;

  @Column(name = "overridden_final_amount")
  private Double overriddenFinalAmount;
}
