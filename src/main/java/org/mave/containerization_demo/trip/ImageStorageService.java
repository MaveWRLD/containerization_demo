package org.mave.containerization_demo.trip;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageStorageService {
    String store(MultipartFile file) throws IOException;
}
