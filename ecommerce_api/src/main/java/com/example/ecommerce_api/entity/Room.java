package com.example.ecommerce_api.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@json_mapping_id")
public class Room extends Auditable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @Column(name = "code")
  private String code;

  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_room_room_category_id"))
  private RoomCategory roomCategory;

  @Column(name = "room_category_id", insertable = false, updatable = false)
  private String roomCategoryId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "floor_id", nullable = false, foreignKey = @ForeignKey(name = "fk_floor_id"))
  private Floor floor;

  @Column(name = "floor_id", insertable = false, updatable = false)
  private String floorId;

  @Column(name = "number_of_bed")
  private Integer numberOfBed;

  @Column(name = "number_of_adult_guest")
  private Integer numberOfAdultGuest;

  @Column(name = "number_of_child_guest")
  private Integer numberOfChildGuest;

  @Column(name = "room_size")
  private String roomSize;

//  @Column(name = "floor_name")
//  private String floorName;

  @Column(name = "description", length = 1000)
  private String description;

  //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
  private List<RoomRateDetail> roomRateDetailList;

  /*@JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @Transient
  private List<RoomRateDetail> roomRateDetailSerdeList = new ArrayList<>();*/

  @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
  private List<Booking> bookingList;

  @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
  private List<BookingDetail> bookingDetailList;

  @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
  private List<RoomFeature> roomFeatureList;
}
