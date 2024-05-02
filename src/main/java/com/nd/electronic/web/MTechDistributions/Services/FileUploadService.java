package com.nd.electronic.web.MTechDistributions.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface FileUploadService {
    String fileupload(MultipartFile file,String path) throws IOException;
    InputStream servefile(String path, String fileName) throws IOException;

    interface ProductService {

    }
}
