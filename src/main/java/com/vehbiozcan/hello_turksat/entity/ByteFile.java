package com.vehbiozcan.hello_turksat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ByteFile {
   private byte[] fileBytes;
   private String originalFileName;
   private String contentType;
}
