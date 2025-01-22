package com.vehbiozcan.hello_turksat.controller;

import org.springframework.http.ResponseEntity;

public interface IFileDownloadController {
    public ResponseEntity<?> downloadFileByte(String fileName);

}
