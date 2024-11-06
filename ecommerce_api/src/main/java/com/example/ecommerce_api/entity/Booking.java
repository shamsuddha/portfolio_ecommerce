package com.example.ecommerce_api.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.example.ecommerce_api.enums.DiscountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "booking")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class, property="@json_mapping_id")
public class Booking extends Auditable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "guest_info_id", nullable = false, foreignKey = @ForeignKey(name = "fk_booking_guest_info_id"))
  private GuestInfo guestInfo;

  @Column(name = "guest_info_id", insertable = false, updatable = false)
  private String guestInfoId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "latest_room_id", nullable = false, foreignKey = @ForeignKey(name = "fk_booking_room_id"))
  private Room room;

  @Column(name = "latest_room_id", insertable = false, updatable = false)
  private String roomId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "coupon_id", foreignKey = @ForeignKey(name = "fk_booking_coupon_id"))
  private Coupon coupon;

  @Column(name = "coupon_id", insertable = false, updatable = false)
  private String couponId;

  @Column(name = "final_discount_percentage")
  private Double finalDiscountPercentage;

  @Column(name = "final_discount_amount")
  private Double finalDiscountAmount;

  @Column(name = "final_amount")
  private Double finalAmount;

  @Column(name = "overridden_final_amount")
  private Double overriddenFinalAmount;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "check_in")
  private LocalDateTime checkIn;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "check_out")
  private LocalDateTime checkOut;

  @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY)
  private List<BookingDetail> bookingDetailList;

  @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY)
  private List<BookingPayable> bookingPayableList;

  @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY)
  private List<BookingReceivable> bookingReceivableList;

}
