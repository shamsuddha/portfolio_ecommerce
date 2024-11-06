package com.example.ecommerce_api.controller;


import io.hms.api.exception.UserInformException;
import io.hms.api.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Objects;

@RestController
@RequestMapping(path = "/file-download", produces = {"application/json"})
@Slf4j
@AllArgsConstructor
public class FileDownloadController {

  private final FileService fileService;

  @GetMapping()
  @ResponseBody
  public ResponseEntity<?> downloadFileByRequestParam(@RequestParam String fileId) {
    System.out.println(fileId);
    Resource file = fileService.getFile(fileId);
        /*Path path = Paths.get("D:\\file-store").resolve("1234534.jpg");
        Resource file = new UrlResource(path.toUri());*/
    if (Objects.isNull(file)) {
      throw new UserInformException("file not found");
    }
    final HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFilename());
    headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);

    return ResponseEntity.ok()
      .headers(headers)
      .contentType(MediaType.APPLICATION_OCTET_STREAM)
      .body(file);
  }

  @PostMapping(value = "/file-in-byte", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public @ResponseBody byte[] downloadFileInByte(@RequestParam String fileId) throws IOException {
    System.out.println(fileId);
    Resource resource = fileService.getFile(fileId);
    if (Objects.isNull(resource)) {
      throw new UserInformException("file not found");
    }
    final InputStream in = new DataInputStream(new FileInputStream(resource.getFile()));
    return IOUtils.toByteArray(in);
  }

  @GetMapping("/forUrl")
  public ResponseEntity<?> downloadFileForUrl(@PathVariable String fileId) {
    try {
      Resource file = fileService.getFile(fileId);
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
      headers.setContentDispositionFormData("filename", file.getFilename());
      byte[] bytes = IOUtils.toByteArray(file.getInputStream());
      return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bytes);

    } catch (IOException e) {
      throw new UserInformException("Something went wrong");
    }
  }

  @GetMapping(path = "/test1")
  public ResponseEntity<?> test1() {
    try {
      final File file = new File("D:/file-store/1234534.jpg");
      final InputStream in = new DataInputStream(new FileInputStream(file));
      final HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
      headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
      return ResponseEntity.ok()
        .headers(headers)
        .body(IOUtils.toByteArray(in));
    } catch (IOException e) {
      return ResponseEntity.ok(null);
    }
  }

  @GetMapping(value = "/test2", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public @ResponseBody byte[] test2() throws IOException {
    final File file = new File("D:/file-store/1234534.jpg");
    final InputStream in = new DataInputStream(new FileInputStream(file));
    return IOUtils.toByteArray(in);
  }

}
