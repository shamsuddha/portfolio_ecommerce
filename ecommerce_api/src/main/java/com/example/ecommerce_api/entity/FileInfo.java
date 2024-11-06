package com.example.ecommerce_api.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.hms.api.enums.FileType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "FileInfo")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class, property="@json_mapping_id")
public class FileInfo extends Auditable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @Column(name = "fileRootPath")
  private String fileRootPath;

  @Column(name = "fileRelativePath")
  private String fileRelativePath;

  @Column(name = "fileNameWithExtension")
  private String fileNameWithExtension;

  @Column(name = "fileTypeKey")
  private FileType fileTypeKey;



}