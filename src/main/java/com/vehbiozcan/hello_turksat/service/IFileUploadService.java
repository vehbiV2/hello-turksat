package com.vehbiozcan.hello_turksat.service;

import com.vehbiozcan.hello_turksat.dto.DtoUploadedFile;
import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {

    public DtoUploadedFile uploadFile(MultipartFile file);
}
