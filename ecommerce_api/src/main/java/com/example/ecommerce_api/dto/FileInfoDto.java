package com.example.ecommerce_api.dto;

import com.example.ecommerce_api.enums.FileType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.core.io.Resource;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class FileInfoDto {
	
	private String id;
	
	private String fileRootPath;
	private String fileRelativePath;
	private String fileNameWithExtension;
	
	private FileType fileTypeKey;
	
	private Resource resource;
	
	public FileInfoDto(
		String id, String fileRootPath, String fileRelativePath, String fileNameWithExtension,
		FileType fileTypeKey, Resource resource) {
		this.id = id;
		this.fileRootPath = fileRootPath;
		this.fileRelativePath = fileRelativePath;
		this.fileNameWithExtension = fileNameWithExtension;
		this.fileTypeKey = fileTypeKey;
		this.resource = resource;
	}
}
