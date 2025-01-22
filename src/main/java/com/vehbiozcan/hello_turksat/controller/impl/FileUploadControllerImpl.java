package com.vehbiozcan.hello_turksat.controller.impl;

import com.vehbiozcan.hello_turksat.controller.IFileUploadController;
import com.vehbiozcan.hello_turksat.dto.DtoUploadedFile;
import com.vehbiozcan.hello_turksat.entity.ByteFile;
import com.vehbiozcan.hello_turksat.entity.RootEntity;
import com.vehbiozcan.hello_turksat.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/uploads")
public class FileUploadControllerImpl implements IFileUploadController {

    @Autowired
    private IFileUploadService fileUploadService;

    @Override
    @PostMapping("file")
    public RootEntity<DtoUploadedFile> uploadFile(@RequestParam("file") MultipartFile file) {

        return RootEntity.ok(fileUploadService.uploadFile(file));
    }

    @PostMapping("file-bytes")
    @Override
    public RootEntity<DtoUploadedFile> uploadFileBytes(@RequestBody byte[] byteFile) {
        System.out.println("Girdi burada şuan");
        return RootEntity.ok(fileUploadService.uploadFileBytes(byteFile));
    }

   /* @Override
    public RootEntity<DtoUploadedFile> uploadFileBytes(@RequestBody ByteFile byteFile) {
        return RootEntity.ok(fileUploadService.uploadFileBytes(byteFile.getFileBytes(), byteFile.getOriginalFileName(), byteFile.getContentType()));
    }*/

}
