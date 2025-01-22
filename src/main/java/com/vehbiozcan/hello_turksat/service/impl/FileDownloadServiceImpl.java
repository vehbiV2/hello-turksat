package com.vehbiozcan.hello_turksat.service.impl;

import com.vehbiozcan.hello_turksat.dto.DtoDownloadFile;
import com.vehbiozcan.hello_turksat.entity.UploadedFile;
import com.vehbiozcan.hello_turksat.exception.BaseException;
import com.vehbiozcan.hello_turksat.exception.ErrorMessage;
import com.vehbiozcan.hello_turksat.exception.MessageType;
import com.vehbiozcan.hello_turksat.repository.UploadedFileRepository;
import com.vehbiozcan.hello_turksat.service.IFileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class FileDownloadServiceImpl implements IFileDownloadService {

    @Value("${file.upload_dir}")
    private String fileUploadDir;

    private Path uploadDir;

    @PostConstruct
    public void init() {
        this.uploadDir = Paths.get(fileUploadDir).toAbsolutePath().normalize();
    }

    @Autowired
    private UploadedFileRepository uploadedFileRepository;

    @Override
    public DtoDownloadFile downloadFile(String fileName) {
        Path filePath = uploadDir.resolve(fileName).normalize();

        File file = filePath.toFile();
        if (!file.exists()) {
            throw new BaseException(new ErrorMessage(MessageType.FILE_NOT_FOUND));
        }


        return null;
    }

    @Override
    public DtoDownloadFile downloadFileByte(String fileName) {

        Optional<UploadedFile> optionalUploadedFile = uploadedFileRepository.findByFileName(fileName);

        if(!optionalUploadedFile.isPresent()) {
            throw new BaseException(new ErrorMessage(MessageType.FILE_NOT_FOUND));
        }

        UploadedFile uploadedFile = optionalUploadedFile.get();

        String filePath = uploadDir.resolve(uploadedFile.getFileName()).toString();

        byte[] file = null;

        try {
           file = Files.readAllBytes(new File(filePath).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return DtoDownloadFile.builder().fileData(file)
                .fileType(uploadedFile.getFileType())
                .fileName(uploadedFile.getFileName())
                .filePath(filePath)
                .build();

    }
}
