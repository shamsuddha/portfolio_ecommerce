package com.example.ecommerce_api.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import com.example.ecommerce_api.dto.FileInfoDto;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class FileUtil {

  @Value("${app.root_path}")
  private String fileRootPath;


  // 1.png
  // ["hotel", "gym", "trademill"]
  // ["jpg", "png", "webp", "gif"]
  public FileInfoDto uploadSingleFile(
    MultipartFile file, List<String> fileRelativePathList, List<String> fileValidExtensions) {
    try {
      String fileRelativePathStr = this.getRelativePath(fileRelativePathList);
      Path rootWithRelativePath = Paths.get(fileRootPath, fileRelativePathStr);
      log.info("File store path {}", rootWithRelativePath.toString());
      if (!Files.exists(rootWithRelativePath)) {
        log.info("File store mkdir {}", rootWithRelativePath.toString());
        this.makeDirectory(fileRootPath, fileRelativePathList, fileRelativePathStr);
      }
      String fileExt = FilenameUtils.getExtension(file.getOriginalFilename());
      if (this.invalidExtension(fileExt, fileValidExtensions)) {
        throw new RuntimeException("Extension not supported");
      }
      String newFileNameWithoutExt = UUID.randomUUID().toString();
      Files.copy(file.getInputStream(), rootWithRelativePath.resolve(newFileNameWithoutExt + "." + fileExt));
      log.info("File store success {}", fileRelativePathStr);

      return new FileInfoDto(null, fileRootPath, fileRelativePathStr, newFileNameWithoutExt + "." + fileExt, null, null);
    } catch (MalformedURLException me) {
      throw new RuntimeException("Error: " + me.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }


  public FileInfoDto downloadSingleFile(String fileRootPath,
                                        String fileRelativePath,
                                        String filenameWithExtension) {
    try {
      if (fileRelativePath == null) {
        throw new RuntimeException("Error: Relative Path can not be null");
      }

      Path path = Paths.get(fileRootPath, fileRelativePath).resolve(filenameWithExtension);
      Resource resource = new UrlResource(path.toUri());
      if (resource.exists() || resource.isReadable()) {
        return new FileInfoDto(null, fileRootPath, fileRelativePath, filenameWithExtension,
          null, resource);
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  public boolean invalidExtension(String fileExtension, List<String> fileValidExtensions) {
    return fileValidExtensions.stream()
      .filter(i -> i.equalsIgnoreCase(fileExtension.toLowerCase()))
      .collect(Collectors.toList()).isEmpty();
  }

  public boolean makeDirectory(String fileRootPath, List<String> fileRelativePathList, String fileRelativePathStr) {
    String tempFileRelativePath = "";
    for (String frp : fileRelativePathList) {
      tempFileRelativePath = tempFileRelativePath + "/" + frp;
      if (!new File(fileRootPath + tempFileRelativePath).exists()) {
        try {
          log.info("File creating dir {}", fileRootPath + tempFileRelativePath);
          Files.createDirectory(Paths.get(fileRootPath + tempFileRelativePath));
        } catch (IOException e) {
          throw new RuntimeException("Could not initialize folder for upload! " + e.getMessage());
        }
      }
    }
    return new File(fileRootPath + fileRelativePathStr).exists();
  }


  //    ["hotel", "gym", "trademill"]
  //    "/hotel/gym/trademill"
  private String getRelativePath(List<String> fileRelativePathList) {
    if (CollectionUtils.isEmpty(fileRelativePathList)) {
      throw new RuntimeException("Error: Relative Path can not be null or empty");
    }
    String objectsCommaSeparated = fileRelativePathList.stream()
      .collect(Collectors.joining("/"));
    System.out.println(objectsCommaSeparated);
    return objectsCommaSeparated;
  }

}
