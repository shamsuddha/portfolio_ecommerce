package com.example.ecommerce_api.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.hms.api.dto.FileInfoDto;
import io.hms.api.enums.FileType;
import io.hms.api.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(path = "/file-upload", produces = {"application/json"})
@Slf4j
@AllArgsConstructor
public class FileUploadController {

  private final FileService fileService;

  @PostMapping("/room-image-upload")
  public ResponseEntity<String> roomImageUpload(
    @RequestPart(value = "file") MultipartFile file,
    @RequestParam String additionalInfo
  ) {
    log.info("file name {}", file.getOriginalFilename());
    log.info("additional info {}", additionalInfo);
    FileInfoDto fileInfoDto = fileService.saveFile(file, FileType.RoomImage);
    return new ResponseEntity("uploaded successfully", HttpStatusCode.valueOf(200));
  }

  @PostMapping("/hotel-image-upload")
  public ResponseEntity<String> hotelImageUpload(@RequestPart(value = "file") MultipartFile file) {
    log.info("Received to upload file request {}", file.getOriginalFilename());
    FileInfoDto fileInfoDto = fileService.saveFile(file, FileType.HotelImage);
    return new ResponseEntity("uploaded successfully", HttpStatusCode.valueOf(200));
  }


  @PostMapping()
  public ResponseEntity<?> uploadFile(
    @RequestParam("file") MultipartFile file,
    @RequestParam String fileInfoRequestDtoStr) {
    log.info("uploading file name {}", file.getOriginalFilename());
    try {
      log.info("Received to upload file request {}", file.getOriginalFilename());
      fileService.saveFile(file, FileType.HotelImage);
      return new ResponseEntity("uploaded successfully", HttpStatusCode.valueOf(200));
    } catch (Exception e) {
      e.printStackTrace();
      log.error("Error in patient registration {}", e.getMessage());
      return new ResponseEntity("file uploaded failed", HttpStatusCode.valueOf(500));
    }
  }

  @PostMapping("/upload1")
  public ResponseEntity<?> upload1(@RequestParam("file") MultipartFile file) {
    try {
      log.info("file name {}", file.getOriginalFilename());
      Path rootWithRelativePath = Paths.get("D:\\file-store");
      Files.copy(file.getInputStream(), rootWithRelativePath.resolve("1234534.jpg"));
      return new ResponseEntity("file uploaded successfully", HttpStatusCode.valueOf(200));

    } catch (Exception e) {
      e.printStackTrace();
      log.error("Error in patient registration {}", e.getMessage());
      return new ResponseEntity("file uploaded failed", HttpStatusCode.valueOf(500));
    }
  }

}
