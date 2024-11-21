package io.hms.api.service.transform;

import io.hms.api.dto.FileInfoDto;
import io.hms.api.entity.FileInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
