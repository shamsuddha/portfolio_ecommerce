package com.example.ecommerce_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "room_rate_detail")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class, property="@json_mapping_id")
public class RoomRateDetail extends Auditable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_room_category_room_category_id"))
  private RoomCategory roomCategory;

  @Column(name = "room_category_id", insertable = false, updatable = false)
  private String roomCategoryId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_rate_type_id", nullable = false, foreignKey = @ForeignKey(name = "fk_room_rate_type_room_rate_type_id"))
  private RoomRateType roomRateType;

  @Column(name = "room_rate_type_id", insertable = false, updatable = false)
  private String roomRateTypeId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_id", nullable = false, foreignKey = @ForeignKey(name = "fk_room_id"))
  private Room room;

  @Column(name = "room_id", insertable = false, updatable = false)
  private String roomId;

  @Column(name = "rate")
  private Double rate;

  @Column(name = "overridden_rate")
  private Double overriddenRate;

  @Column(name = "hour")
  private Double hour;

}
