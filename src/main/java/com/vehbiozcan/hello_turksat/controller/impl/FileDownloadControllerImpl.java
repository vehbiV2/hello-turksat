package com.vehbiozcan.hello_turksat.controller.impl;

import com.vehbiozcan.hello_turksat.controller.IFileDownloadController;
import com.vehbiozcan.hello_turksat.dto.DtoDownloadFile;
import com.vehbiozcan.hello_turksat.service.IFileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/files")
public class FileDownloadControllerImpl implements IFileDownloadController {

    @Autowired
    private IFileDownloadService fileDownloadService;

    @GetMapping("view/{fileName}")
    @Override
    public ResponseEntity<?> downloadFileByte(@PathVariable("fileName") String fileName) {
        DtoDownloadFile file = fileDownloadService.downloadFileByte(fileName);
        return ResponseEntity.ok().contentType(MediaType.valueOf(file.getFileType())).body(file.getFileData()) ;
    }
}
