package com.vehbiozcan.hello_turksat.controller;

import com.vehbiozcan.hello_turksat.dto.DtoUploadedFile;
import com.vehbiozcan.hello_turksat.entity.RootEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadController {
    public RootEntity<DtoUploadedFile> uploadFile(MultipartFile file);
}
