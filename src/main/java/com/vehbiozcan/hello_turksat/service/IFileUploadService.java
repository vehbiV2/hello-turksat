package com.vehbiozcan.hello_turksat.service;

import com.vehbiozcan.hello_turksat.dto.DtoUploadedFile;
import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {

    public DtoUploadedFile uploadFile(MultipartFile file);
    /*public DtoUploadedFile uploadFileBytes(byte[] fileBytes, String originalFileName, String contentType);*/
    public DtoUploadedFile uploadFileBytes(byte[] fileBytes);
}
