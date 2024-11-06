package com.example.ecommerce_api.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "room_category")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class, property="@json_mapping_id")
public class RoomCategory extends Auditable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @Column(name = "name")
  private String name;

  @Column(name = "code")
  private String code;

  @OneToMany(mappedBy = "roomCategory", fetch = FetchType.LAZY)
  private List<RoomRateDetail> roomRateDetailList;

  @OneToMany(mappedBy = "roomCategory", fetch = FetchType.LAZY)
  private List<RoomCategoryRateType> roomCategoryRateTypeList;

  @OneToMany(mappedBy = "roomCategory", fetch = FetchType.LAZY)
  private List<Room> roomList;

}
