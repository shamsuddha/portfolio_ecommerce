package com.example.ecommerce_api.entity;


import com.example.ecommerce_api.enums.RoomStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking_detail")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class, property="@json_mapping_id")
public class BookingDetail extends Auditable {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "booking_id", foreignKey = @ForeignKey(name = "fk_booking_detail_booking_id"))
  private Booking booking;

  @Column(name = "booking_id", insertable = false, updatable = false)
  private String bookingId;

  @Column(name = "number_of_day")
  private Double numberOfDay;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "time_slot_start")
  private LocalDateTime timeSlotStart;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "time_slot_end")
  private LocalDateTime timeSlotEnd;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_id", nullable = false, foreignKey = @ForeignKey(name = "fk_booking_detail_room_id"))
  private Room room;

  @Column(name = "room_id", insertable = false, updatable = false)
  private String roomId;

  @Column(name = "rate_amount")
  private Double rateAmount;

  @Column(name = "overridden_rate_amount")
  private Double overriddenRateAmount;

  @Column(name = "number_of_adult_guest")
  private Integer numberOfAdultGuest;

  @Column(name = "number_of_child_guest")
  private Integer numberOfChildGuest;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "coupon_id", foreignKey = @ForeignKey(name = "fk_booking_detail_coupon_id"))
  private Coupon coupon;

  @Column(name = "coupon_id", insertable = false, updatable = false)
  private String couponId;

  @Column(name = "discount_percentage")
  private Double discountPercentage;

  @Column(name = "discount_amount")
  private Double discountAmount;

  @Column(name = "final_amount")
  private Double finalAmount;

  @Column(name = "overridden_final_amount")
  private Double overriddenFinalAmount;

//  @NotNull
//  @Column(name = "room_status", nullable = false)
//  @Enumerated(EnumType.STRING)
//  private RoomStatus roomStatus;

  @Column(name = "room_status_key", nullable = false)
  @Enumerated(EnumType.STRING)
  private RoomStatus roomStatusKey;
  @Column(name = "room_status_value")
  private RoomStatus roomStatusValue;

}
