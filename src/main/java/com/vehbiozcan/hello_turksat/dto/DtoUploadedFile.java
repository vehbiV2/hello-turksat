package com.vehbiozcan.hello_turksat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoUploadedFile {

    private Long id;

    private String fileName;
    private String fileType;

    private String filePath;
    private Long fileSize;
    private Double fileSizeKb;
    private Date uploadDate;

}
