package com.example.ecommerce_api.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "room_feature_template_detail")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class, property="@json_mapping_id")
public class RoomFeatureTemplateDetail extends Auditable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_feature_template_id", nullable = false, foreignKey = @ForeignKey(name = "fk_room_feature_template_detail_room_feature_template_id"))
  private RoomFeatureTemplate roomFeatureTemplate;

  @Column(name = "room_feature_template_id", insertable = false, updatable = false)
  private String roomFeatureTemplateId;

  @Column(name = "quantity")
  private Double quantity;

  @Column(name = "rate")
  private Double rate;

  @Column(name = "total_amount")
  private Double totalAmount;

  @OneToMany(mappedBy ="roomFeatureTemplateDetail",  fetch = FetchType.LAZY)
  private List<RoomFeature> roomFeatureList;

}
