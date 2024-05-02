package com.nd.electronic.web.MTechDistributions.Services.impl;

import com.nd.electronic.web.MTechDistributions.Services.FileUploadService;
import com.nd.electronic.web.MTechDistributions.exceptionUtils.BadApiRequest;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
@Service
public class FileUploadServiceimpl implements FileUploadService
{
    Logger logs= LoggerFactory.getLogger(FileUploadServiceimpl.class);
    @Override
    public String fileupload(MultipartFile file, String path) throws IOException {
        final String originalFileName = file.getOriginalFilename();
        final String extension = originalFileName.substring(originalFileName.indexOf("."));
        final String fileName = UUID.randomUUID() + extension;
        logs.info("File Name {}"+fileName);
        final String fileNameWithPath=path+ File.separator+fileName;
        logs.info("File with full path {}"+fileNameWithPath);
        if(extension.equalsIgnoreCase(".png")||extension.equalsIgnoreCase(".jpeg")||extension.equalsIgnoreCase(".jpg"))
        {
            File folder = new File(path);
            if(!folder.exists()){
                folder.mkdirs();
            }
            Files.copy(file.getInputStream(), Path.of(fileNameWithPath));
            return fileNameWithPath;
        }else {
            throw new BadApiRequest();
        }


    }

    @Override
    public InputStream servefile(String path, String fileName) throws IOException {
        String fullFilePathName = path + fileName;
        InputStream inpStram = Files.newInputStream(Path.of(fullFilePathName));
        return inpStram;

    }

}
