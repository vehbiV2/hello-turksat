package com.vehbiozcan.hello_turksat.service.impl;

import com.vehbiozcan.hello_turksat.dto.DtoUploadedFile;
import com.vehbiozcan.hello_turksat.entity.UploadedFile;
import com.vehbiozcan.hello_turksat.exception.BaseException;
import com.vehbiozcan.hello_turksat.exception.ErrorMessage;
import com.vehbiozcan.hello_turksat.exception.MessageType;
import com.vehbiozcan.hello_turksat.jwt.utils.JwtUtils;
import com.vehbiozcan.hello_turksat.repository.UploadedFileRepository;
import com.vehbiozcan.hello_turksat.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements IFileUploadService {

    @Value("${file.upload_dir}")
    private String uploadDir;

    @Autowired
    private UploadedFileRepository uploadedFileRepository;

    @Override
    public DtoUploadedFile uploadFile(MultipartFile file) {

        if(file.isEmpty() || file == null){
            throw new BaseException(new ErrorMessage(MessageType.FILE_EMPTY));
        }


        // Dosyanın content type nı aldık
        String contentType = file.getContentType();

        if (!contentType.contains("pdf")) {
            throw new BaseException(new ErrorMessage(MessageType.FILE_TYPE_NOT_SUPPORTED));
        }

        // Orginal dosya adı burada
        String orgFileName = file.getOriginalFilename();
        // Yeni dosya adını SecurityContext içinden aldığımız username ile oluşturuduk
        String uploadedFileName = JwtUtils.getUserNameFromSecurityContextHolder() + "_" + UUID.randomUUID().toString() + "_" + (orgFileName != null ? orgFileName : "unknown") ;

        // Directory yi oluşturduk
        File dir = new File(uploadDir);

        // directitory var mı yok mu bakıyoruz yoksa oluşturuyoruz?
        if (!dir.exists()) {
            // directory yoksa tüm alt directorylerine bakarak sırayla oluşturur
            dir.mkdirs();
        }

        String filePath = uploadDir + uploadedFileName;

        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        UploadedFile uploadedFile = new UploadedFile();
        uploadedFile.setFileName(uploadedFileName);
        uploadedFile.setFileType(contentType);
        uploadedFile.setFilePath(filePath);
        uploadedFile.setFileSize(file.getSize());

        UploadedFile savedFile = uploadedFileRepository.save(uploadedFile);

         DtoUploadedFile savedDtoFile = DtoUploadedFile.builder()
                 .id(savedFile.getId())
                 .fileName(savedFile.getFileName())
                 .fileType(savedFile.getFileType())
                 .filePath(savedFile.getFilePath())
                 .fileSize(savedFile.getFileSize())
                 .fileSizeKb((savedFile.getFileSize() / 1024.0))
                 .uploadDate(savedFile.getUploadDate())
                 .build();

        return savedDtoFile;
    }



/*

    @Override
    public DtoUploadedFile uploadFileBytes(byte[] fileBytes, String originalFileName, String contentType) {

        if (fileBytes == null || fileBytes.length == 0) {
            throw new BaseException(new ErrorMessage(MessageType.FILE_EMPTY));
        }

        if (!isPdf(fileBytes)) {
            throw new BaseException(new ErrorMessage(MessageType.FILE_TYPE_NOT_SUPPORTED));
        }

        // Yeni dosya adını oluşturuyoruz
        String uploadedFileName = JwtUtils.getUserNameFromSecurityContextHolder() + "_" + UUID.randomUUID().toString() + "_" + originalFileName +".pdf";

        // Directory yi oluşturuyoruz
        File dir = new File(uploadDir);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filePath = uploadDir + uploadedFileName;

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(fileBytes);  // Byte array'i dosyaya yazıyoruz
        } catch (IOException e) {
            throw new RuntimeException("File saving error", e);
        }

        // Dosya bilgilerini kaydediyoruz
        UploadedFile uploadedFile = new UploadedFile();
        uploadedFile.setFileName(uploadedFileName);
        uploadedFile.setFileType(contentType);
        uploadedFile.setFilePath(filePath);
        uploadedFile.setFileSize((long) fileBytes.length);

        UploadedFile savedFile = uploadedFileRepository.save(uploadedFile);

        return DtoUploadedFile.builder()
                .id(savedFile.getId())
                .fileName(savedFile.getFileName())
                .fileType(savedFile.getFileType())
                .filePath(savedFile.getFilePath())
                .fileSize(savedFile.getFileSize())
                .fileSizeKb((savedFile.getFileSize() / 1024.0))
                .uploadDate(savedFile.getUploadDate())
                .build();
    }
*/


    @Override
    public DtoUploadedFile uploadFileBytes(byte[] fileBytes) {

        if (fileBytes == null || fileBytes.length == 0) {
            throw new BaseException(new ErrorMessage(MessageType.FILE_EMPTY));
        }

        if (!isPdf(fileBytes)) {
            throw new BaseException(new ErrorMessage(MessageType.FILE_TYPE_NOT_SUPPORTED));
        }

        // Yeni dosya adını oluşturuyoruz
        String uploadedFileName = JwtUtils.getUserNameFromSecurityContextHolder() + "_" + UUID.randomUUID().toString() + "_byteFiles" + ".pdf";

        // Directory yi oluşturuyoruz
        File dir = new File(uploadDir);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filePath = uploadDir + uploadedFileName;

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(fileBytes);  // Byte array'i dosyaya yazıyoruz
        } catch (IOException e) {
            throw new BaseException(new ErrorMessage(MessageType.FILE_UPLOAD_FAILED));
        }

        // Dosya bilgilerini kaydediyoruz
        UploadedFile uploadedFile = new UploadedFile();
        uploadedFile.setFileName(uploadedFileName);
        uploadedFile.setFileType("application/pdf");
        uploadedFile.setFilePath(filePath);
        uploadedFile.setFileSize((long) fileBytes.length);

        UploadedFile savedFile = uploadedFileRepository.save(uploadedFile);

        return DtoUploadedFile.builder()
                .id(savedFile.getId())
                .fileName(savedFile.getFileName())
                .fileType(savedFile.getFileType())
                .filePath(savedFile.getFilePath())
                .fileSize(savedFile.getFileSize())
                .fileSizeKb((savedFile.getFileSize() / 1024.0))
                .uploadDate(savedFile.getUploadDate())
                .build();
    }

    private boolean isPdf(byte[] fileBytes) {
        // PDF dosyalarının başında %PDF ile başlar
        return (fileBytes[0] == 0x25 && fileBytes[1] == 0x50 && fileBytes[2] == 0x44 && fileBytes[3] == 0x46);
    }


}
