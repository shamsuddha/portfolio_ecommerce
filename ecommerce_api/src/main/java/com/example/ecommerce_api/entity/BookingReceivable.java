package com.example.ecommerce_api.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.hms.api.enums.DiscountType;
import io.hms.api.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "booking_receivable")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class, property="@json_mapping_id")
public class BookingReceivable extends Auditable {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "booking_id", foreignKey = @ForeignKey(name = "fk_booking_receivable_booking_id"))
  private Booking booking;

  @Column(name = "booking_id", insertable = false, updatable = false)
  private String bookingId;

  @Column(name = "amount")
  private Double amount;

  @Column(name = "payment_type_key", nullable = false)
  @Enumerated(EnumType.STRING)
  private PaymentType paymentTypeKey;
  @Column(name = "payment_type_value")
  private PaymentType paymentTypeValue;

//  @Column(name = "payment_type")
//  @Enumerated(EnumType.STRING)
//  private PaymentType paymentType;
//

}
