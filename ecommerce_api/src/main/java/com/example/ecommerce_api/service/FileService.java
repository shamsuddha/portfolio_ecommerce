package com.example.ecommerce_api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.hms.api.common.FileUtil;
import io.hms.api.dto.FileInfoDto;
import io.hms.api.entity.FileInfo;
import io.hms.api.enums.FileType;
import io.hms.api.exception.UserInformException;
import io.hms.api.repository.FileInfoRepository;
import io.hms.api.service.transform.FileServiceTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

  private final FileUtil fileUtil;
  private final FileInfoRepository fileInfoRepository;
  private final FileServiceTransform fileServiceTransform;

  public FileInfoDto saveFile(MultipartFile file, FileType fileType) {

    FileInfoDto fileInfoDto;
    if(Objects.equals(fileType,FileType.RoomImage)){
      if (file.getSize() > 15 * 1000 * 1000) {
        throw new UserInformException("Room image size can not be more than 15 MB");
      }
      fileInfoDto = this.fileUtil.uploadSingleFile(
        file,
        List.of("hotel","room_images"),
        List.of("jpg", "jpeg", "png", "webp"));
      fileInfoDto.setFileTypeKey(FileType.RoomImage);
    }else if(Objects.equals(fileType,FileType.HotelImage)){
      if (file.getSize() > 20 * 1000 * 1000) {
        throw new UserInformException("Hotel image size can not be more than 20 MB");
      }
      fileInfoDto = this.fileUtil.uploadSingleFile(
        file,
        List.of("hotel","hotel_images"),
        List.of("jpg", "jpeg", "png", "webp"));
      fileInfoDto.setFileTypeKey(FileType.RoomImage);
    }else{
      throw new RuntimeException("");
    }
    FileInfo fileInfo = this.fileServiceTransform.getFileInfo(new FileInfo(), fileInfoDto);
    FileInfo savedFileInfo = fileInfoRepository.save(fileInfo);
    return this.fileServiceTransform.getFileInfoDto(savedFileInfo, new FileInfoDto());

  }

  public Resource getFile(String fileId) {
    try {
      if (Objects.isNull(fileId)) {
        throw new RuntimeException("FileId needed");
      }
      Optional<FileInfo> fileInfoOp = fileInfoRepository.findById(fileId);
      if (fileInfoOp.isPresent()) {
        return this.fileUtil.downloadSingleFile(
            fileInfoOp.get().getFileRootPath(),
            fileInfoOp.get().getFileRelativePath(),
            fileInfoOp.get().getFileNameWithExtension())
          .getResource();
      }
      throw new RuntimeException("File not found");
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Error");
    }
  }

}
