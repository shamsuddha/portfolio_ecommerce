package com.example.ecommerce_api.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "room_feature")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class, property="@json_mapping_id")
public class RoomFeature extends Auditable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_id", nullable = false, foreignKey = @ForeignKey(name = "fk_room_feature_room_id"))
  private Room room;

  @Column(name = "room_id", insertable = false, updatable = false)
  private String roomId;

/*  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_feature_template_id", nullable = false, foreignKey = @ForeignKey(name = "fk_room_feature_room_feature_template_id"))
  private RoomFeatureTemplate roomFeatureTemplate;

  @Column(name = "room_feature_template_id", insertable = false, updatable = false)
  private String roomFeatureTemplateId;*/

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_feature_template_detail_id", foreignKey = @ForeignKey(name = "fk_room_feature_room_feature_template_detail_id"))
  private RoomFeatureTemplateDetail roomFeatureTemplateDetail;

  @Column(name = "room_feature_template_detail_id", insertable = false, updatable = false)
  private String roomFeatureTemplateDetailId;


  @Column(name = "quantity")
  private Double quantity;

  @Column(name = "rate")
  private Double rate;

  @Column(name = "total_amount")
  private Double totalAmount;

  @Column(name = "overridden_total_amount")
  private Double overriddenTotalAmount;



}
