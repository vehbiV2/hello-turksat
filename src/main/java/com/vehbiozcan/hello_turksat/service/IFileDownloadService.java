package com.vehbiozcan.hello_turksat.service;

import com.vehbiozcan.hello_turksat.dto.DtoDownloadFile;

public interface IFileDownloadService {

    public DtoDownloadFile downloadFile(String fileName);
    public DtoDownloadFile downloadFileByte(String fileName);


}
