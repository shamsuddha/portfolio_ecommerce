package com.example.ecommerce_api.service.transform;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.example.ecommerce_api.dto.FileInfoDto;
import com.example.ecommerce_api.entity.FileInfo;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceTransform {

  public static FileInfo getFileInfo(FileInfo fileInfo, FileInfoDto fileInfoDto) {
    return fileInfo
      .setId(fileInfoDto.getId())
      .setFileRootPath(fileInfoDto.getFileRootPath())
      .setFileRelativePath(fileInfoDto.getFileRelativePath())
      .setFileRootPath(fileInfoDto.getFileRootPath())
      .setFileNameWithExtension(fileInfoDto.getFileNameWithExtension())
      .setFileTypeKey(fileInfoDto.getFileTypeKey());
  }

  public static FileInfoDto getFileInfoDto(FileInfo fileInfo, FileInfoDto fileInfoDto) {
    return fileInfoDto
      .setId(fileInfo.getId())
      .setFileRootPath(fileInfo.getFileRootPath())
      .setFileRelativePath(fileInfo.getFileRelativePath())
      .setFileRootPath(fileInfo.getFileRootPath())
      .setFileNameWithExtension(fileInfo.getFileNameWithExtension())
      .setFileTypeKey(fileInfo.getFileTypeKey());
  }
}
