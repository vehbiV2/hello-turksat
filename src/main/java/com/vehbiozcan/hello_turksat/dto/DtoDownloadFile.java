package com.vehbiozcan.hello_turksat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoDownloadFile {
    private String fileName;
    private String fileType;
    private String filePath;
    private byte[] fileData;
}
